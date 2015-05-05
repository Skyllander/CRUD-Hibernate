package crud.controller;

import java.util.Calendar;
import java.util.List;

import crud.model.*;
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

	public String cadastra(String nome) {

		String[] entrada = nome.split("_");
		for (String s : entrada) {
			System.out.println(s);
		}
		
		Usuario usuario = new Usuario();
		
		usuario.nome = entrada[0];
		usuario.CPF = entrada[1];
		usuario.dataNascimento = Calendar.getInstance();
		usuario.sexo = Sexo.valueOf(entrada[3]);
		usuario.cargo = Cargo.buscaPorNome(entrada[4]);
		usuario.perfis.add(Perfil.buscaPorNome(entrada[5]));
		usuario.dataCadastro = Calendar.getInstance();
		
		try{
			usuario.cadastra();
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