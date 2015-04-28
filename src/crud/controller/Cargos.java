 package crud.controller;

import java.util.ArrayList;

import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import crud.model.*;

public class Cargos {
	
	CargoDAO dao;
	
	public Cargos() {
		dao = new CargoDAO(new JPAUtil().getEntityManager());
	}
	
	public <T>boolean editaNome(T tag, String nome) {
		return dao.editaNome(tag, nome);
	}
	
	public <T>boolean removeNome(T tag) {
		return dao.removeNome(tag);
	}
	
	public boolean cadastra(String nome) {
		
		boolean ok = false;
		try{
			dao.cadastra(nome);
		}
		catch(NoResultException e) {
			ok = true;
		}
		catch(PersistenceException e) {
		}
		return ok;
	}
	
	public List<String> listaNome() {
		List<Cargo> lista = dao.lista();
		List<String> nomes = new ArrayList<String>();
		for (Cargo c : lista) {
			nomes.add(c.nome);
		}
		return nomes;
	}
	
	public List<Integer> listaID() {
		List<Cargo> lista = dao.lista();
		List<Integer> ids = new ArrayList<Integer>();
		for (Cargo c : lista) {
			ids.add(c.id);
		}
		return ids;
	}
	
}