package crud.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.ConstraintViolation;

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
	public String CPF;
	
	@Column(name = "DATA_NASCIMENTO")
	public Calendar dataNascimento;
	
	@Column(name = "SEXO")
	public Sexo sexo;
	
	
	@ManyToOne
	public Cargo cargo;
	
	@ManyToMany
	public List<Perfil> perfis;
	
	@Column(name = "DATA_CADASTRO")
	public Calendar dataCadastro;
	
	public Usuario() {
		perfis = new ArrayList<Perfil>();
	}

	public Usuario (String nome) {
		this.nome = nome;
		perfis = new ArrayList<Perfil>();
	}

	public void cadastra() {
		validar();
		dao.adiciona(this);
	}

	public void remove() {
		dao.remove(this);
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
		validarHibernateValidator();
		validarMesmoNome();
		Validate.check(this.nome);
	}

	private void validarMesmoNome() {

		Usuario mesmoNome = dao.buscaPorNome(this.nome);

		if (mesmoNome != null) {
			throw new ValidationException("Nome de usuario ja existente");
		}

	}

	private void validarHibernateValidator () {
		Set<ConstraintViolation<Usuario>> validate = Validate.hibernateCheck(this);
		for (ConstraintViolation<Usuario> constraintViolation : validate) {
			System.out.println(constraintViolation.getMessage());
			throw new ValidationException("Campo Nome obrigatorio");
		}
	}
	
}
