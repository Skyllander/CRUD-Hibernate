package crud.controller;

import java.util.List;

import crud.model.*;
import execoes.ValidationException;

public class Cargos implements Controller<Cargo> {

	public Cargos() {
	}
	
	private <T>Cargo buscaPorNomeOuId(T tag) {
		if (tag.getClass().equals(String.class)) {
			return Cargo.buscaPorNome((String)tag);
		}
		else return Cargo.buscaPorId((Integer)tag);
	}

	public <T>String edita(T tag, String nome) {
		Cargo cargo = buscaPorNomeOuId(tag);
		if (cargo == null) return "Nao econtrado";
		else {
			try {
				cargo.editaNome(nome);
			}
			catch(ValidationException e) {
				return e.getMessage();
			}
			return "Editado com Sucesso";
		}
	}

	public <T>String remove(T tag) {
		Cargo cargo = buscaPorNomeOuId(tag);
		if (cargo == null) return "Nao econtrado";
		else {
			cargo.remove();
			return "Removido com sucesso";
		}
	}

	public String cadastra(String entrada) {

		Cargo cargo = new Cargo();
		cargo.nome = entrada;
		try{
			cargo.cadastra();
		}
		catch(ValidationException e) {
			return e.getMessage();
		}
		return "Inserido com sucesso";
	}
	
	public List<Cargo> listaOrdenadoPorNome() {
		return Cargo.listaOrdenadoPorNome();
	}

}