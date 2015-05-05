package crud.model;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public abstract class JPAUtil {
	
	private static EntityManagerFactory entityManagerFactory;
	public static EntityManager em;
	
	static {
		entityManagerFactory = Persistence.createEntityManagerFactory("sistemacrud");
		em = getEntityManager();
		init();
	}

	public static EntityManager getEntityManager() {
		return entityManagerFactory.createEntityManager();
	}

	private static void init() {
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

}