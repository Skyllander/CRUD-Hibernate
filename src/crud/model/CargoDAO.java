package crud.model;

import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class CargoDAO extends DAO<Cargo>{

	public CargoDAO() {
		super(Cargo.class);
	}

	public Cargo buscaPorNome(String nome) {
		try {
			return (Cargo)em.createQuery("from Cargo where nome = '" + nome + "'").getSingleResult();
		}
		catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Cargo> listaOrdenadoPorNome() {
		Query query = em.createQuery("from Cargo order by nome");
		return query.getResultList();
	}

}
