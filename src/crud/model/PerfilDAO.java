package crud.model;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

public class PerfilDAO extends DAO<Perfil>{

	PerfilDAO() {
		super(Perfil.class);
	}

	public Perfil buscaPorNome(String nome) {

		Query query = em.createQuery("from Perfil where nome = :nome");
		query.setParameter("nome", nome);
		try {
			return (Perfil)query.getSingleResult();
		}
		catch (NoResultException e) {
			return null;
		}
	}

	public boolean isVinculado(Integer id) {
		Query query = em.createQuery("select u from Perfil p LEFT JOIN p.usuarios u WHERE"
				+ " u.active = true AND p.id = :id");
		query.setParameter("id", id);

		if (query.getResultList().isEmpty()) 
			return false;
		else return true;

	}

	@SuppressWarnings("unchecked")
	public List<Perfil> listaOrdenadoPorNome() {
		Query query = em.createQuery("from Perfil order by nome");
		return query.getResultList();
	}

}
