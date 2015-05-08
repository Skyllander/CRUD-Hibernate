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
	
	
	//TODO checar active na busca / mudar parametro
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
	
	public List<Usuario> listaFiltro(String nome, String cargo, String perfil) {
		Query query = em.createQuery("SELECT u FROM Usuario u JOIN u.perfis p WHERE"
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
			query.setParameter("perfil", perfil);
		
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
	
	@SuppressWarnings("unchecked")
	public List<Usuario> listaNome(String arg) {
		Query query = em.createQuery("from Usuario where nome like :arg");
		query.setParameter("arg", "%" + arg.trim() + "%");
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> listaCargo(String arg) {
		Query query = em.createQuery("from Usuario where cargo.nome = :arg");
		query.setParameter("arg", arg.trim());
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> listaPerfil(String arg) {
		Query query = em.createQuery("from Usuario u JOIN cargo c where c.nome = :arg");
		query.setParameter("arg", arg.trim());
		return query.getResultList();
	}

}
