package crud.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.ConstraintViolation;

import execoes.ValidationException;

@Entity
@Table(name = "PERFIL", uniqueConstraints = {
		@UniqueConstraint(columnNames ="ID"),
		@UniqueConstraint(columnNames = "NOME") })

public class Perfil extends Model{

	@Transient
	private static final PerfilDAO dao = new PerfilDAO();
	
	@ManyToMany(mappedBy="perfis")
	List<Usuario> usuarios;

	public Perfil() {
		this.nome = "NaoDefinido";
		usuarios = new ArrayList<Usuario>();
	}

	public Perfil (String nome) {
		this.nome = nome;
		usuarios = new ArrayList<Usuario>();
	}

	public void cadastra() {
		validar();
		dao.adiciona(this);
	}

	public void remove() {
		boolean free = true;
		if (!usuarios.isEmpty()) {
			for (Usuario user : usuarios) {
				if (user.active) free = false;
			}
		}
		if (free) dao.remove(this);
		else throw new ValidationException("Perfil se encontra vinculado a Usuario");
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
		Validate.checkNome(this.nome);
		validarMesmoNome();
	}

	private void validarMesmoNome() {

		Perfil mesmoNome = dao.buscaPorNome(this.nome);

		if (mesmoNome != null) {
			throw new ValidationException("Nome de usuario ja existente");
		}

	}

	private void validarHibernateValidator () {
		Set<ConstraintViolation<Perfil>> validate = Validate.hibernateCheck(this);
		for (ConstraintViolation<Perfil> constraintViolation : validate) {
			System.out.println(constraintViolation.getMessage());
			throw new ValidationException("Campo Nome obrigatorio");
		}
	}

}