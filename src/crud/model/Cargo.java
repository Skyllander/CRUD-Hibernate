package crud.model;
import java.util.List;

import javax.persistence.*;

import execoes.ValidationException;

@Entity
@Table(name = "CARGO", uniqueConstraints = {
		@UniqueConstraint(columnNames ="ID"),
		@UniqueConstraint(columnNames = "NOME") })

public class Cargo extends Model{

	@Transient
	private static final CargoDAO dao = new CargoDAO();

	public Cargo() {
		this.nome = "NaoDefinido";
	}

	public Cargo (String nome) {
		this.nome = nome;
	}

	public void cadastra() {
		validar();
		dao.adiciona(this);
	}

	public void remove() {
		if (!isVinculado()) dao.remove(this);
		else throw new ValidationException("Cargo se encontra vinculado a Usuario");
	}

	public boolean isVinculado() {
		return dao.isVinculado(this.id);
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
		Validate.checkHibernate(this);
		Validate.checkNome(this.nome);
		validarMesmoNome();
	}

	private void validarMesmoNome() {

		Cargo mesmoNome = dao.buscaPorNome(this.nome);

		if (mesmoNome != null) {
			throw new ValidationException("Nome de usuario ja existente");
		}

	}

}