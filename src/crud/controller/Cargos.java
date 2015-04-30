package crud.controller;

import java.util.ArrayList;
import java.util.List;
import crud.model.*;
import execoes.ValidationException;

public class Cargos {

	public Cargos() {

	}

	public <T>String editaCargo(T tag, String nome) {
		Cargo cargo;
		if (tag.getClass().equals(String.class)) {
			cargo = Cargo.buscaPorNome((String)tag);
		}
		else cargo = Cargo.buscaPorId((Integer)tag);
		try {
			cargo.editaNome(nome);
		}
		catch(ValidationException e) {
			return e.getMessage();
		}
		catch(NullPointerException e) {
			return "Nao encontrado";
		}
		return "Editado com Sucesso";
	}

	public <T>String removeCargo(T tag) {
		Cargo cargo;
		if (tag.getClass().equals(String.class)) {
			cargo = Cargo.buscaPorNome((String)tag);
		}
		else cargo = Cargo.buscaPorId((Integer)tag);
		try {
			cargo.remove();
		}
		catch(NullPointerException e) {
			return "Nao encontrado";
		}
		return "Removido com sucesso";
	}

	public String cadastra(String nome) {

		Cargo cargo = new Cargo();
		cargo.nome = nome;
		try{
			cargo.cadastra();
		}
		catch(ValidationException e) {
			return e.getMessage();
		}
		return "Inserido com sucesso";
	}

	public List<String> listaNomeOrdenado() {
		List<Cargo> lista = Cargo.listaOrdenadoPorNome();
		List<String> nomes = new ArrayList<String>();
		for (Cargo c : lista) {
			nomes.add(c.nome);
		}
		return nomes;
	}

	public List<Integer> listaID() {
		List<Cargo> lista = Cargo.listaOrdenadoPorNome();
		List<Integer> ids = new ArrayList<Integer>();
		for (Cargo c : lista) {
			ids.add(c.id);
		}
		return ids;
	}

}