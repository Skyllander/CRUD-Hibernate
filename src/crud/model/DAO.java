package crud.model;

import java.util.List;

import javax.persistence.EntityManager;

public class DAO<T> {

	private final EntityManager em;
	private final Class<T> classe;
	
	public DAO(EntityManager em, Class<T> classe) {
		this.em = em;
		this.classe = classe;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> busca(Integer id){
		try {
			return em.createQuery("from " + classe.getName() + " where id = '" + id + "'").getResultList();
		}
		catch (Exception NoResultException) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<T> busca(String nome){
		try {
			return em.createQuery("from " + classe.getName() + " where nome = '" + nome + "'").getResultList();
		}
		catch (Exception NoResultException) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<T> lista() {
		return em.createQuery("select e from " + classe.getName() + " e").getResultList();
	}
	
	public void adiciona(T t) {
		em.persist(t);
	}
	
	public void remove(T t) {
		em.remove(t);
	}
	
}
