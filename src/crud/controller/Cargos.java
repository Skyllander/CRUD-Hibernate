package crud.controller;

import javax.persistence.EntityManager;

import crud.model.*;

public class Cargos {
	
	private final EntityManager em;
	
	public void cadastra(String nome) {
		Cargo cargo = new Cargo(nome);
		
		CargoDAO dao = new CargoDAO(em);
		init();
		dao.adiciona(cargo);
		commit();
	}
	
	public Cargos() {
		em = new JPAUtil().getEntityManager();
	}
	
	private void init() {
		em.getTransaction().begin();
	}
	
	private void commit() {
		em.getTransaction().commit();
	}
	
	public void close() {
		em.close();
	}
	
}
