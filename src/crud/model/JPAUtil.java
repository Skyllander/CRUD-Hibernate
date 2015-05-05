package crud.model;

import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;


public abstract class JPAUtil {
	
	private static EntityManagerFactory entityManagerFactory;
	public static EntityManager em;
	
	private static ValidatorFactory validatorFactory =  
			Validation.buildDefaultValidatorFactory();
	public static Validator validator = getValidator();
	
	static {
		entityManagerFactory = Persistence.createEntityManagerFactory("sistemacrud");
		em = getEntityManager();
		init();
	}
	
	public static Validator getValidator() {
		return validatorFactory.getValidator();
	}

	public static <T> Set<ConstraintViolation<T>> validate(T obj) {
		return validator.validate(obj);
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