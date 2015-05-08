package crud.controller;

import java.util.List;

public abstract interface Controller<E> {

	public <T>String edita(T tag, String nome);

	public <T>String remove(T tag);

	public String cadastra(String entrada);
	
	public List<E> listaOrdenadoPorNome();
	
}