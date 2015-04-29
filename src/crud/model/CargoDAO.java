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
	
	public CargoDAO() {
		super(Cargo.class);
	}
	
	
	public <T>boolean editaNome(T tag, String nome) {
		
		return false;//TODO Refatorar metodo
		
		
//		List<Cargo> encontrados;
//		if (tag.getClass().equals(String.class)) {
//			encontrados = buscaN((String)tag);
//			Pattern p = Pattern.compile("[0-9]*.*[0-9]+.*[0-9]*");
//			Matcher m = p.matcher(nome);
//			if (encontrados.isEmpty() || m.matches()) return false;
//		}
//		else {
//			encontrados = busca((Integer)tag);
//			if (encontrados.isEmpty()) return false;
//		}
//		boolean valido = true;
//		init();
//		for (Cargo enc : encontrados) {
//			try {
//				enc.nome = nome;
//			}
//			catch(EntityNotFoundException e){
//				valido = false;
//			}
//			commit();
//		}
//		return valido;
	}
	
	public <T>boolean removeNome(T tag) {
		
		
		
		return false;//TODO Refatorar este metodo
//		List<Cargo> encontrados;
//		if (tag.getClass().equals(String.class)) {
//			encontrados = buscaN((String)tag);
//			if (encontrados.isEmpty()) return false;
//		}
//		else {
//			encontrados = busca((Integer)tag);
//			if (encontrados.isEmpty()) return false;
//		}
//		boolean valido = true;
//		init();
//		for (Cargo enc : encontrados)
//		{
//			try {
//				remove(enc);
//			}
//			catch(EntityNotFoundException e){
//				valido = false;
//			}
//			try {
//				commit();
//			}
//			catch (RollbackException e) {
//				valido = false;
//			}
//		}
//		return valido;
	}
	
	@SuppressWarnings("unchecked")
	public Cargo buscaPorNome(String nome) {
		try {
			return (Cargo)em.createQuery("from Cargo where nome = '" + nome + "'").getSingleResult();
		}
		catch(NoResultException e) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Cargo> listaN() {
		Query query = em.createQuery("from Cargo order by nome");
		return query.getResultList();
	}
	

	
}
