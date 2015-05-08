package crud.model;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

public class CargoDAO extends DAO<Cargo>{

	CargoDAO() {
		super(Cargo.class);
	}

	public Cargo buscaPorNome(String nome) {
		try {
			Query query = em.createQuery("from Cargo where nome = :nome");
			query.setParameter("nome", nome);
			return (Cargo)query.getSingleResult();
		}
		catch (NoResultException e) {
			return null;
		}
		catch (NullPointerException e) {
			return null;
		}
	}

	public boolean isVinculado(Integer id) {
		Query query = em.createQuery("select u from Cargo c LEFT JOIN c.usuarios u WHERE"
				+ " u.active = true AND c.id = :id");
		query.setParameter("id", id);

		if (query.getResultList().isEmpty()) 
			return false;
		else return true;

	}

	@SuppressWarnings("unchecked")
	public List<Cargo> listaOrdenadoPorNome() {
		Query query = em.createQuery("from Cargo order by nome");
		return (List<Cargo>)query.getResultList();
	}

}
