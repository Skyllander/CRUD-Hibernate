package crud.model;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.ConstraintViolation;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import execoes.ValidationException;

@Entity
@Table(name = "USUARIO", uniqueConstraints = {
		@UniqueConstraint(columnNames ="ID")})

public class Usuario extends Model{

	@Transient
	private static final UsuarioDAO dao = new UsuarioDAO();
	
	@NotBlank
	@Column(name = "CPF")
	public String cpf;
	
	@Column(name = "DATA_NASCIMENTO")
	public Calendar dataNascimento;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "SEXO")
	public Sexo sexo;
	
	@NotNull
	@ManyToOne
	public Cargo cargo;
	
	@ManyToMany(fetch=FetchType.EAGER)
	public List<Perfil> perfis;
	
	@Column(name = "DATA_CADASTRO")
	public Calendar dataCadastro;
	
	@Column(name = "ACTIVE")
	public boolean active;
	
	public Usuario() {
		perfis = new ArrayList<Perfil>();
	}

	public Usuario (String nome) {
		this.nome = nome;
		perfis = new ArrayList<Perfil>();
	}
	
	private void define(String dados, boolean edita) {
		
		String[] entrada = dados.split(",");
		Perfil encontrado = null;
		String nomePerfil = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		if (edita) {
			detach();
		}
		
		nome = entrada[0].trim();
		cpf = entrada[1].trim();
		
		dataNascimento = Calendar.getInstance();
		try {
			dataNascimento.setTime(sdf.parse(entrada[2]));
		} catch (ParseException e) {
			throw new ValidationException("Formato de data invalido");
		}
		
		try {
			sexo = Sexo.valueOf(entrada[3].trim());
		}
		catch (IllegalArgumentException e) {
			throw new ValidationException("Sexo invalido");
		}
		
		cargo = Cargo.buscaPorNome(entrada[4].trim());
	
		for(int i = 5; i < entrada.length; ++i) {
			nomePerfil = entrada[i].trim();
			if (!nomePerfil.isEmpty()) {
				encontrado = Perfil.buscaPorNome(nomePerfil);
				if (encontrado != null) perfis.add(encontrado);
				else {
					throw new ValidationException("Perfil '" + nomePerfil + "' nao existe");
				}
				encontrado = null;
			}
		}
		
		if (!edita) dataCadastro = Calendar.getInstance();
	}

	public void cadastra(String dados) {
		
		define(dados, false);
		active = true;
		validar();
		dao.adiciona(this);
		
		//TODO nao precisa
		this.cargo.usuarios.add(this);
		for (Perfil perf : this.perfis) {
			perf.usuarios.add(this);
		}
		dao.commit();
	}
	
	private void detach() {
		
		for (Perfil perf : this.perfis) {
			perf.usuarios.remove(this);
		}
		this.perfis.clear();
		
		this.cargo.usuarios.remove(this);
		
	}

	public void remove() {
		detach();
		this.active = false;
		dao.commit();
	}

	public <T>void edita(String entrada) {
		dao.detach(this);
		
		define(entrada, true);
		
		validar();
		dao.merge(this);
		dao.commit();
	}

	public static List<Usuario> listaOrdenadoPorNome() {
		return dao.listaOrdenadoPorNome();
	}
	
	//TODO filtrar como um todo
	
	public static List<Usuario> listaFiltro(String nome, String cargo, String perfil) {
		return dao.listaFiltro(nome, cargo, perfil);
	}
	
	public static List<Usuario> listaNome(String arg) {
		return dao.listaNome(arg);
	}
	
	public static List<Usuario> listaCargo(String arg) {
		return dao.listaCargo(arg);
	}
	
	public static List<Usuario> listaPerfil(String arg) {
		return dao.listaPerfil(arg);
	}

	public static Usuario buscaPorNome(String nome) {
		return dao.buscaPorNome(nome);
	}

	public static Usuario buscaPorId(Integer id) {
		return dao.buscaPorId(id);
	}
	


	private void validar() {
		Validate.checkNome(this.nome);
		Validate.checkNumero(this.cpf);
		validaCargoObrigatorio();
		validarHibernateValidator();
		validarMesmoCPF();
	}
	
	private void validaCargoObrigatorio() {
		
		if (cargo == null) {
			throw new ValidationException("Cargo nao encontrado");
		}
		
	}

	private void validarMesmoCPF() {

		Usuario mesmoNome = dao.buscaPorCPF(this.cpf);

		if (mesmoNome != null) {
			throw new ValidationException("CPF de usuario ja cadastrado");
		}

	}

	private void validarHibernateValidator () {
		Set<ConstraintViolation<Usuario>> validate = Validate.hibernateCheck(this);
		for (ConstraintViolation<Usuario> constraintViolation : validate) {
			System.out.println(constraintViolation.getMessage());
			throw new ValidationException("Campo obrigatorio (*) deixado em branco");
		}
	}
	
}
