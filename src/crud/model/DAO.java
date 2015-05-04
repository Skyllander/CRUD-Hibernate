package crud.model;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public abstract class DAO<T> {

	protected EntityManager em;
	private final Class<T> classe;

	DAO(Class<T> classe) {
		this.em = JPAUtil.getEntityManager();
		System.out.println("EM: " + em);
		this.classe = classe;
	}

	@SuppressWarnings("unchecked")
	public T buscaPorId(Integer id) {
		Query query = em.createQuery("from " + classe.getName() + " where id = :nID ");
		query.setParameter("nID", id);
		try {
			return (T)query.getSingleResult();
		}
		catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public T lista() {
		Query query = em.createQuery("from " + classe.getName());
		return (T)query.getSingleResult();
	}

	public void adiciona(T obj) {
		em.persist(obj);
		commit();
	}

	public void remove(T obj) {
		em.remove(obj);
		commit();
	}

	public void init() {
		em.getTransaction().begin();
	}

	public void commit() {
		em.getTransaction().commit();
		init();
	}

	public void close() {
		em.getTransaction().commit();
		em.close();
	}
	
	public void merge(T obj) {
		em.merge(obj);
	}
	
	public void detach(T obj) {
		em.detach(obj);
	}

}
