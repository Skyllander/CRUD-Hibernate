package crud.model;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

public class UsuarioDAO extends DAO<Usuario>{

	UsuarioDAO() {
		super(Usuario.class);
	}

	public Usuario buscaPorNome(String nome) {
		try {
			return (Usuario)em.createQuery("from Usuario where nome = '" + nome + "'").getSingleResult();
		}
		catch (NoResultException e) {
			return null;
		}
		catch (NullPointerException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> listaOrdenadoPorNome() {
		Query query = em.createQuery("from Usuario order by nome");
		return query.getResultList();
	}

}
