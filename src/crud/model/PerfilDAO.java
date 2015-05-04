package crud.model;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

public class PerfilDAO extends DAO<Perfil>{

	PerfilDAO() {
		super(Perfil.class);
	}

	public Perfil buscaPorNome(String nome) {
		try {
			return (Perfil)em.createQuery("from Perfil where nome = '" + nome + "'").getSingleResult();
		}
		catch (NoResultException e) {
			return null;
		}
		catch (NullPointerException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Perfil> listaOrdenadoPorNome() {
		Query query = em.createQuery("from Perfil order by nome");
		return query.getResultList();
	}

}
