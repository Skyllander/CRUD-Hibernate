package crud.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.RollbackException;

import crud.model.*;

public class Cargos {
	
	private final EntityManager em;
	CargoDAO dao;
	
	public Cargos() {
		em = new JPAUtil().getEntityManager();
		dao = new CargoDAO(em);
	}
	
	public <T>boolean editaNome(T tag, String nome) {
		List<Cargo> encontrados;
		Cargo encontrado;
		if (tag.getClass().equals(String.class)) {
			encontrados = dao.busca((String)tag);
			if (encontrados.size() < 2 && !encontrados.isEmpty()) 
				encontrado = encontrados.get(0);
			else return false;
		}
		else {
			encontrados = dao.busca((Integer)tag);
			if (encontrados.size() < 2 && !encontrados.isEmpty()) 
				encontrado = encontrados.get(0);
			else return false;
		}
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
	
	public <T>boolean removeNome(T tag) {
		List<Cargo> encontrados;
		Cargo encontrado;
		if (tag.getClass().equals(String.class)) {
			encontrados = dao.busca((String)tag);
			if (encontrados.size() < 2 && !encontrados.isEmpty()) 
				encontrado = encontrados.get(0);
			else return false;
		}
		else {
			encontrados = dao.busca((Integer)tag);
			if (encontrados.size() < 2 && !encontrados.isEmpty()) 
				encontrado = encontrados.get(0);
			else return false;
		}
		boolean valido = true;
		if (encontrado != null) {
			init();
			try {
				dao.remove(encontrado);
			}
			catch(EntityNotFoundException e){
				valido = false;
			}
			try {
				commit();
			}
			catch (RollbackException e) {
				valido = false;
			}
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
	
	public List<String> listaNome() {
		List<Cargo> lista = dao.lista();
		List<String> nomes = new ArrayList<String>();
		Collections.sort(lista);
		for (Cargo c : lista) {
			nomes.add(c.getNome());
		}
		return nomes;
	}
	
	public List<Integer> listaID() {
		List<Cargo> lista = dao.lista();
		List<Integer> ids = new ArrayList<Integer>();
		Collections.sort(lista);
		for (Cargo c : lista) {
			ids.add(c.getId());
		}
		return ids;
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
