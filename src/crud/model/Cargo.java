package crud.model;

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
	private CargoDAO dao = new CargoDAO();

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

	private void validarCargoMesmoNome(){

		Cargo cargoMesmoNome = dao.buscaPorNome(this.nome);

		if (cargoMesmoNome != null) {
			throw new ValidationException("Nome de usuario ja existente");
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

	private void validar() {
		validarHibernateValidator();
		validarCargoMesmoNome();
		validarNomeComNumeros();

	}

	public void cadastra() {

		validar();
		dao.adiciona(this);

	}

}