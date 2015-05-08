package crud.model;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;
import execoes.ValidationException;

@Entity
@Table(name = "USUARIO", uniqueConstraints = {
		@UniqueConstraint(columnNames ="ID")})

public class Usuario extends Model {

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
		this.nome = "Nao Definido";
		perfis = new ArrayList<Perfil>();
		active = false;
	}

	public Usuario (String nome) {
		this.nome = nome;
		perfis = new ArrayList<Perfil>();
		active = false;
	}
	
	private void define(String dados, boolean edita) {
		
		String[] entrada = dados.split(",");
		Perfil encontrado = null;
		String nomePerfil = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
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
		dao.commit();
	}

	public void remove() {
		active = false;
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
	
	public static List<Usuario> listaFiltro(String nome, String cargo, String perfil) {
		return dao.listaFiltro(nome, cargo, perfil);
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
		Validate.checkHibernate(this);
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


	
}
