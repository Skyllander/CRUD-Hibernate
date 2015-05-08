package crud.model;
import java.util.ArrayList;
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
	
	@OneToMany(mappedBy="cargo")
	List<Usuario> usuarios;

	public Cargo() {
		this.nome = "NaoDefinido";
		usuarios = new ArrayList<Usuario>();
	}

	public Cargo (String nome) {
		this.nome = nome;
		usuarios = new ArrayList<Usuario>();
	}

	public void cadastra() {
		validar();
		dao.adiciona(this);
	}

	
	//TODO passar para metodo(validar se existe usuario) e fazer select direto
	public void remove() {
		boolean free = true;
		if (!usuarios.isEmpty()) {
			for (Usuario user : usuarios) {
				if (user.active) free = false;
			}
		}
		if (free) dao.remove(this);
		else throw new ValidationException("Cargo se encontra vinculado a Usuario");
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
		Validate.checkNome(this.nome);
		validarMesmoNome();
	}

	private void validarMesmoNome() {

		Cargo mesmoNome = dao.buscaPorNome(this.nome);

		if (mesmoNome != null) {
			throw new ValidationException("Nome de usuario ja existente");
		}

	}

	//TODO passar para o validate
	private void validarHibernateValidator () {
		Set<ConstraintViolation<Cargo>> validate = Validate.hibernateCheck(this);
		for (ConstraintViolation<Cargo> constraintViolation : validate) {
			System.out.println(constraintViolation.getMessage());
			throw new ValidationException("Campo Nome obrigatorio");
		}
	}

}