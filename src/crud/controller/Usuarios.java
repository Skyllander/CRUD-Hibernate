package crud.controller;

import java.util.List;
import crud.model.Usuario;
import execoes.ValidationException;

public class Usuarios implements Controller<Usuario> {

	public Usuarios() {
	}
	
	private <T>Usuario buscaPorNomeOuId(T tag) {
		if (tag.getClass().equals(String.class)) {
			return Usuario.buscaPorNome((String)tag);
		}
		else return Usuario.buscaPorId((Integer)tag);
	}

	public <T>String edita(T tag, String nome) {
		Usuario usuario = buscaPorNomeOuId(tag);
		if (usuario == null) return "Nao econtrado";
		else {
			try {
				usuario.editaNome(nome);
			}
			catch(ValidationException e) {
				return e.getMessage();
			}
			return "Editado com Sucesso";
		}
	}

	public <T>String remove(T tag) {
		Usuario usuario = buscaPorNomeOuId(tag);
		if (usuario == null) return "Nao econtrado";
		else {
			usuario.remove();
			return "Removido com sucesso";
		}
	}

	public String cadastra(String dados) {

		Usuario usuario = new Usuario();
		
		try{
			usuario.cadastra(dados);
		}
		catch(ValidationException e) {
			return e.getMessage();
		}
		return "Inserido com sucesso";
	}
	
	public List<Usuario> listaOrdenadoPorNome() {
		return Usuario.listaOrdenadoPorNome();
	}

}