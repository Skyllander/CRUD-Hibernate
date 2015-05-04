package crud.model;

import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.persistence.*;
import javax.validation.ConstraintViolation;
import org.hibernate.validator.constraints.NotBlank;
import execoes.ValidationException;

@Entity
@Table(name = "PERFIL", uniqueConstraints = {
		@UniqueConstraint(columnNames ="ID"),
		@UniqueConstraint(columnNames = "NOME") })

public class Perfil {

	@Transient
	private static final PerfilDAO dao = new PerfilDAO();

	@Id
	@GeneratedValue
	@Column(name = "ID", unique = true, nullable = false)
	public Integer id;

	@NotBlank
	@Column(name = "NOME", unique = true, nullable = false)
	public String nome;

	public Perfil() {

	}

	public Perfil (String nome) {
		this.nome = nome;
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

	public static List<Perfil> listaOrdenadoPorNome() {
		return dao.listaOrdenadoPorNome();
	}

	public static Perfil buscaPorNome(String nome) {
		return dao.buscaPorNome(nome);
	}

	public static Perfil buscaPorId(Integer id) {
		return dao.buscaPorId(id);
	}

	private void validar() {
		validarHibernateValidator();
		validarMesmoNome();
		validarNomeComNumeros();
		validarInicioMaiscula();
	}

	private void validarMesmoNome() {

		Perfil mesmoNome = dao.buscaPorNome(this.nome);

		if (mesmoNome != null) {
			throw new ValidationException("Nome de usuario ja existente");
		}

	}

	private void validarInicioMaiscula() {

		Pattern p = Pattern.compile("[A-Z]+.*");
		Matcher m = p.matcher(nome);

		if (!m.matches()) {
			throw new ValidationException("Nome deve ser iniciado com letra maiuscula");
		}
	}

	private void validarNomeComNumeros() {

		Pattern p = Pattern.compile("[0-9]*.*[0-9]+.*[0-9]*");
		Matcher m = p.matcher(nome);

		if (m.matches()) {
			throw new ValidationException("Nome nao pode conter numeros");
		}
	}

	private void validarHibernateValidator () {
		Set<ConstraintViolation<Perfil>> validate = JPAUtil.validate(this);
		for (ConstraintViolation<Perfil> constraintViolation : validate) {
			System.out.println(constraintViolation.getMessage());
			throw new ValidationException("Campo Nome obrigatorio");
		}
	}

}