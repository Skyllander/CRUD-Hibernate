package crud.model;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.RollbackException;

public class CargoDAO extends DAO<Cargo>{
	
	public CargoDAO(EntityManager em) {
		super(em, Cargo.class);
	}
	
	public void cadastra(String nome) throws PersistenceException, NoResultException {
		
		Cargo cargo = new Cargo(nome);
		
		Query query = em.createQuery("from Cargo where nome = :n");
		query.setParameter("n", nome);
		
		try {
			query.getSingleResult();
		}
		catch(NoResultException e) {
			Pattern p = Pattern.compile("[0-9]*.*[0-9]+.*[0-9]*");
			Matcher m = p.matcher(nome);
			if (nome.isEmpty() || m.matches()) 
				throw new PersistenceException();
			init();
			adiciona(cargo);
			commit();
			throw e;
		}
		
	}
	
	public <T>boolean editaNome(T tag, String nome) {
		List<Cargo> encontrados;
		if (tag.getClass().equals(String.class)) {
			encontrados = buscaN((String)tag);
			Pattern p = Pattern.compile("[0-9]*.*[0-9]+.*[0-9]*");
			Matcher m = p.matcher(nome);
			if (encontrados.isEmpty() || m.matches()) return false;
		}
		else {
			encontrados = busca((Integer)tag);
			if (encontrados.isEmpty()) return false;
		}
		boolean valido = true;
		init();
		for (Cargo enc : encontrados) {
			try {
				enc.nome = nome;
			}
			catch(EntityNotFoundException e){
				valido = false;
			}
			commit();
		}
		return valido;
	}
	
	public <T>boolean removeNome(T tag) {
		List<Cargo> encontrados;
		if (tag.getClass().equals(String.class)) {
			encontrados = buscaN((String)tag);
			if (encontrados.isEmpty()) return false;
		}
		else {
			encontrados = busca((Integer)tag);
			if (encontrados.isEmpty()) return false;
		}
		boolean valido = true;
		init();
		for (Cargo enc : encontrados)
		{
			try {
				remove(enc);
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
		}
		return valido;
	}
	
	@SuppressWarnings("unchecked")
	public List<Cargo> buscaN(String nome) throws NoResultException {
			return em.createQuery("from Cargo where nome = '" + nome + "'").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Cargo> listaN() {
		Query query = em.createQuery("from Cargo order by nome");
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Cargo> lista() {
		Query query = em.createQuery("from Cargo order by nome");
		return query.getResultList();
	}
	
}
