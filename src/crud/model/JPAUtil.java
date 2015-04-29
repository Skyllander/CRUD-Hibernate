package crud.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	public static EntityManager em;
	private static EntityManagerFactory entityManagerFactory;
	
	static {
		entityManagerFactory = 
				Persistence.createEntityManagerFactory("sistemacrud");
		em = new JPAUtil().getEntityManager();
		init();
	}
	
	public EntityManager getEntityManager() {
		return entityManagerFactory.createEntityManager();
	}
	
	private static void init() {
		em.getTransaction().begin();
	}
	
	public static void commit() {
		em.getTransaction().commit();
		init();
	}
	
	public static void close() {
		em.getTransaction().commit();
		em.close();
	}
	
}