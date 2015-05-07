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
			Usuario encontrado = (Usuario)em.createQuery("from Usuario where nome = '" + nome + "'").getSingleResult();
			if (!encontrado.active) throw new NoResultException();
			return encontrado;
		}
		catch (NoResultException e) {
			return null;
		}
		catch (NullPointerException e) {
			return null;
		}
	}
	
	public Usuario buscaPorCPF(String cpf) {
		try {
			Usuario encontrado = (Usuario)em.createQuery("from Usuario where cpf= '" + cpf + "'").getSingleResult();
			if (!encontrado.active) throw new NoResultException();
			return encontrado;
		}
		catch (NoResultException e) {
			return null;
		}
		catch (NullPointerException e) {
			return null;
		}
	}
	
	public Usuario buscaPorId(Integer id) {
		Query query = em.createQuery("from Usuario where id = :nID ");
		query.setParameter("nID", id);
		try {
			Usuario encontrado = (Usuario)query.getSingleResult();
			if (!encontrado.active) throw new NoResultException();
			return encontrado;
		}
		catch (NoResultException e) {
			return null;
		}
		catch (NullPointerException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> listaOrdenadoPorNome() {
		Query query = em.createQuery("from Usuario order by nome");
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
