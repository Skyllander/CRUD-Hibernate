package crud.model;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

public class UsuarioDAO extends DAO<Usuario>{

	UsuarioDAO() {
		super(Usuario.class);
	}

	public Usuario buscaPorNome(String nome) {
		try {
			Query query = em.createQuery("from Usuario WHERE active = true AND nome = :nome");
			query.setParameter("nome", nome);
			return (Usuario)query.getSingleResult();
		}
		catch (NoResultException e) {
			return null;
		}
	}
	public Usuario buscaPorCPF(String cpf) {
		try {
			Query query = em.createQuery("from Usuario WHERE active = true AND cpf = :cpf");
			query.setParameter("cpf", cpf);
			return (Usuario)query.getSingleResult();
		}
		catch (NoResultException e) {
			return null;
		}
	}
	
	public Usuario buscaPorId(Integer id) {
		Query query = em.createQuery("FROM Usuario WHERE active = true AND id = :nID");
		query.setParameter("nID", id);
		try {
			return (Usuario)query.getSingleResult();
		}
		catch (NoResultException e) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> listaFiltro(String nome, String cargo, String perfil) {
		Query query = em.createQuery("SELECT DISTINCT u FROM Usuario u LEFT JOIN u.perfis p WHERE"
				+ " u.active = true AND (u.nome like :nome OR :nome IS NULL)"
				+ " AND (u.cargo.nome = :cargo OR :cargo IS NULL)"
				+ " AND (p.nome = :perfil OR :perfil IS NULL)");
		
		if (nome.isEmpty()) 
			query.setParameter("nome", null);
		else 
			query.setParameter("nome", "%" + nome.trim() + "%");
		
		if (cargo.isEmpty()) 
			query.setParameter("cargo", null);
		else 
			query.setParameter("cargo", cargo);
		
		if (perfil.isEmpty()) 
			query.setParameter("perfil", null);
		else 
			query.setParameter("perfil", perfil.trim());
		
		try {
			return query.getResultList();
		}
		catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> listaOrdenadoPorNome() {
		Query query = em.createQuery("from Usuario where active = true order by nome");
		return query.getResultList();
	}

}
