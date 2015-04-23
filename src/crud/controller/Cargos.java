package crud.controller;

import javax.persistence.EntityManager;

import crud.model.*;

public class Cargos {
	
	private final EntityManager em;
	
	public Cargos() {
		em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		em.getTransaction().commit();
		em.close();
	}
	
}
