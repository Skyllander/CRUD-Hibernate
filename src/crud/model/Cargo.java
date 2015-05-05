package crud.model;

import java.util.List;
import java.util.Set;
import javax.persistence.*;
import javax.validation.ConstraintViolation;
import execoes.ValidationException;

@Entity
@Table(name = "CARGO", uniqueConstraints = {
		@UniqueConstraint(columnNames ="ID"),
		@UniqueConstraint(columnNames = "NOME") })

public class Cargo extends Model{

	@Transient
	private static final CargoDAO dao = new CargoDAO();

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
		validarMesmoNome();
		Validate.check(this.nome);
	}

	private void validarMesmoNome() {

		Cargo mesmoNome = dao.buscaPorNome(this.nome);

		if (mesmoNome != null) {
			throw new ValidationException("Nome de usuario ja existente");
		}

	}

	private void validarHibernateValidator () {
		Set<ConstraintViolation<Cargo>> validate = Validate.hibernateCheck(this);
		for (ConstraintViolation<Cargo> constraintViolation : validate) {
			System.out.println(constraintViolation.getMessage());
			throw new ValidationException("Campo Nome obrigatorio");
		}
	}

}