package crud.model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class DAO<T> {

	protected final EntityManager em;
	private final Class<T> classe;
	
	public DAO(EntityManager em, Class<T> classe) {
		this.em = em;
		this.classe = classe;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> busca(Integer id) throws NoResultException{
		Query query = em.createQuery("from " + classe.getName() + " where id = :nID ");
		query.setParameter("nID", id);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> lista() {
		Query query = em.createQuery("from " + classe.getName());
		return query.getResultList();
	}
	
	public void adiciona(T t) {
		em.persist(t);
	}
	
	public void remove(T t) {
		em.remove(t);
	}
	
	protected void init() {
		em.getTransaction().begin();
	}
	
	protected void commit() {
		em.getTransaction().commit();
	}
	
	protected void close() {
		em.close();
	}
	
}
