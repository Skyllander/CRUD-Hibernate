package crud.model;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.ConstraintViolation;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
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
	
	//TODO Algo estranho na busca de perfis
	
	@ManyToMany
	public List<Perfil> perfis;
	
	@Column(name = "DATA_CADASTRO")
	public Calendar dataCadastro;
	
	@Type(type = "sim_nao")
	@Column(name = "ACTIVE")
	public boolean active;
	
	public Usuario() {
		perfis = new ArrayList<Perfil>();
		active = true;
	}

	public Usuario (String nome) {
		this.nome = nome;
		perfis = new ArrayList<Perfil>();
		active = true;
	}

	public void cadastra(String dados) {
		
		String[] entrada = dados.split(",");
		Perfil encontrado = null;
		String nomePerfil = "";
		
		for (String s : entrada) {
			System.out.println(s);
		}
		
		nome = entrada[0].trim();
		cpf = entrada[1].trim();
		dataNascimento = Calendar.getInstance();
		
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
		
		dataCadastro = Calendar.getInstance();
		
		validar();
		dao.adiciona(this);
	}

	public void remove() {
		this.active = false;
		//dao.remove(this);
	}

	public <T>void editaNome(String nome) {
		dao.detach(this);
		this.nome = nome;
		validar();
		dao.merge(this);
		dao.commit();
	}

	public static List<Usuario> listaOrdenadoPorNome() {
		return dao.listaOrdenadoPorNome();
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
			throw new ValidationException("Campo obrigatorio (*) foi deixado em branco");
		}
	}
	
}
