package crud.model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class DAO<T> {

	protected final EntityManager em;
	private final Class<T> classe;
	
	public DAO(Class<T> classe) {
		this.em = JPAUtil.em;
		this.classe = classe;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<T> busca(Integer id) throws NoResultException{
		//TODO Ignorar NoResultEception
		Query query = em.createQuery("from " + classe.getName() + " where id = :nID ");
		query.setParameter("nID", id);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> lista() {
		Query query = em.createQuery("from " + classe.getName());
		return query.getResultList();
	}
	
	public void adiciona(T obj) {
		em.persist(obj);
		JPAUtil.commit();
	}
	
	public void remove(T obj) {
		em.remove(obj);
		JPAUtil.commit();
	}
	
	protected void close() {
		em.close();
	}
	
}
