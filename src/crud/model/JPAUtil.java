package crud.model;

import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;


public class JPAUtil {

	private static EntityManagerFactory entityManagerFactory;
	public static EntityManager em;
	private static ValidatorFactory validatorFactory;
	public static Validator validator;

	static {
		System.out.println(em);
		entityManagerFactory = 
				Persistence.createEntityManagerFactory("sistemacrud");
		System.out.println(em);
		em = getEntityManager();
		
		System.out.println(em);
		
		validatorFactory = 
				Validation.buildDefaultValidatorFactory();
		validator = getValidator();
		init();
	}

	public static EntityManager getEntityManager() {
		return entityManagerFactory.createEntityManager();
	}
	
	public EntityManager getEM() {
		return em;
	}

	public static Validator getValidator() {
		return validatorFactory.getValidator();
	}

	public static <T> Set<ConstraintViolation<T>> validate(T obj) {
		return validator.validate(obj);
	}
	
	public static void load() {
		
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