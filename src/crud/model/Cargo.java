package crud.model;

import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.ConstraintViolation;

import org.hibernate.validator.constraints.NotBlank;

import execoes.ValidationException;

@Entity
@Table(name = "CARGO", uniqueConstraints = {
		@UniqueConstraint(columnNames ="ID"),
		@UniqueConstraint(columnNames = "NOME") })

public class Cargo{

	@Transient
	private static CargoDAO dao = new CargoDAO();

	@Id
	@GeneratedValue
	@Column(name = "ID", unique = true, nullable = false)
	public Integer id;

	@NotBlank
	@Column(name = "NOME", unique = true, nullable = false)
	public String nome;

	public Cargo() {

	}

	public Cargo (String nome) {
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

	public static List<Cargo> listaOrdenadoPorNome() {
		return dao.listaOrdenadoPorNome();
	}

	public static Cargo buscaPorNome(String nome) {
		return dao.buscaPorNome(nome);
	}

	public static Cargo buscaPorId(Integer id) {
		return dao.buscaPorId(id);
	}

	private void validar() {
		validarHibernateValidator();
		validarCargoMesmoNome();
		validarNomeComNumeros();
		validarInicioMaiscula();
	}

	private void validarCargoMesmoNome() {

		Cargo cargoMesmoNome = dao.buscaPorNome(this.nome);

		if (cargoMesmoNome != null) {
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
		Set<ConstraintViolation<Cargo>> validate = JPAUtil.validate(this);
		for (ConstraintViolation<Cargo> constraintViolation : validate) {
			System.out.println(constraintViolation.getMessage());
			throw new ValidationException("Campo Nome obrigatorio");
		}
	}

}