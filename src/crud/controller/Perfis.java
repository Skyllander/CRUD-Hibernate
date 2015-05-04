package crud.controller;

import java.util.List;
import crud.model.*;
import execoes.ValidationException;

public class Perfis implements Controller<Perfil> {

	public Perfis() {
	}
	
	private <T>Perfil buscaPorNomeOuId(T tag) {
		if (tag.getClass().equals(String.class)) {
			return Perfil.buscaPorNome((String)tag);
		}
		else return Perfil.buscaPorId((Integer)tag);
	}

	public <T>String edita(T tag, String nome) {
		Perfil perfil = buscaPorNomeOuId(tag);
		if (perfil == null) return "Nao econtrado";
		else {
			try {
				perfil.editaNome(nome);
			}
			catch(ValidationException e) {
				return e.getMessage();
			}
			return "Editado com Sucesso";
		}
	}

	public <T>String remove(T tag) {
		Perfil perfil = buscaPorNomeOuId(tag);
		if (perfil == null) return "Nao econtrado";
		else {
			perfil.remove();
			return "Removido com sucesso";
		}
	}

	public String cadastra(String nome) {

		Perfil perfil = new Perfil();
		perfil.nome = nome;
		try{
			perfil.cadastra();
		}
		catch(ValidationException e) {
			return e.getMessage();
		}
		return "Inserido com sucesso";
	}
	
	public List<Perfil> listaOrdenadoPorNome() {
		return Perfil.listaOrdenadoPorNome();
	}

}