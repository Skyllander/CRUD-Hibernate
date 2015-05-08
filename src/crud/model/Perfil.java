package crud.model;
import java.util.List;

import javax.persistence.*;

import execoes.ValidationException;

@Entity
@Table(name = "PERFIL", uniqueConstraints = {
		@UniqueConstraint(columnNames ="ID"),
		@UniqueConstraint(columnNames = "NOME") })

public class Perfil extends Model{

	@Transient
	private static final PerfilDAO dao = new PerfilDAO();

	public Perfil() {
		this.nome = "NaoDefinido";
	}

	public Perfil (String nome) {
		this.nome = nome;
	}

	public void cadastra() {
		validar();
		dao.adiciona(this);
	}

	public void remove() {
		if (!isVinculado())
			dao.remove(this);
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

	public boolean isVinculado() {
		return dao.isVinculado(this.id);
	}

	private void validar() {
		Validate.checkHibernate(this);
		Validate.checkNome(this.nome);
		validarMesmoNome();
	}

	private void validarMesmoNome() {

		Perfil mesmoNome = dao.buscaPorNome(this.nome);

		if (mesmoNome != null) {
			throw new ValidationException("Nome de usuario ja existente");
		}

	}

}