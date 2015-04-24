package crud.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

import crud.model.*;

public class Cargos {
	
	private final EntityManager em;
	CargoDAO dao;
	
	public Cargos() {
		em = new JPAUtil().getEntityManager();
		dao = new CargoDAO(em);
	}
	
	public boolean editaNome(int id, String nome) {
		Cargo encontrado = dao.busca(id);
		boolean valido = true;
		if (encontrado != null) {
			init();
			try {
				encontrado.setNome(nome);
			}
			catch(EntityNotFoundException e){
				valido = false;
			}
			commit();
			return valido;
		}
		else return false;
	}
	
	public boolean editaNome(String vNome, String nNome) {
		Cargo encontrado = dao.busca(vNome);
		boolean valido = true;
		if (encontrado != null) {
			init();
			try {
				encontrado.setNome(nNome);
			}
			catch(EntityNotFoundException e){
				valido = false;
			}
			commit();
			return valido;
		}
		else return false;
	}
	
	public boolean cadastra(String nome) {
		
		Cargo cargo = new Cargo(nome);
		
		List<Cargo> lista = dao.lista();
		boolean repete = false;
		if (nome.length() > 0) {
			for(Cargo c : lista) {
				if(c.getNome().equals(nome)) {
					repete = true;
				}
			}
			if (!repete) {
				init();
				dao.adiciona(cargo);
				commit();
				return true;
			}
			else return false;
		}
		else return false;
	}
	
	public List<String> lista() {
		List<Cargo> lista = dao.lista();
		List<String> nomes = new ArrayList<String>();
		for (Cargo c : lista) {
			nomes.add(c.getNome());
		}
		Collections.sort(nomes);
		return nomes;
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
