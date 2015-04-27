package crud.model;

import java.util.List;

import javax.persistence.EntityManager;

public class CargoDAO {

	private final DAO<Cargo> dao;
	
	public CargoDAO(EntityManager em) {
		dao = new DAO<Cargo>(em, Cargo.class);
	}
	
	public void adiciona(Cargo t){
		dao.adiciona(t);
	}
	
	public List<Cargo> busca(Integer id) {
		return dao.busca(id);
	}
	
	public List<Cargo> busca(String nome) {
		return dao.busca(nome);
	}
	
	public List<Cargo> lista() {
		return dao.lista();
	}
	
	public void remove(Cargo t) {
		dao.remove(t);
	}
	
}
