package crud.view;

import java.util.Scanner;

import crud.controller.Controller;

public class MenuEdita<E> extends View {
	
	private final Controller<E> control;
	
	MenuEdita(Scanner scan, Controller<E> control, String titulo) {
		super(scan);
		this.control = control;
		opcoes.add("Buscar ID");
		opcoes.add("Buscar Nome");
		this.titulo = titulo;
	}
	
	private <T>void editaNome(T tag, String nome) {
		System.out.println("[*" + control.edita(tag, nome) + "*]");
	}
	
	public void init() {
		mostraTitulo();
		Integer select = -1;
		select = listaOpcao();
		boolean sair = false;
		while (!sair) {
			switch(select)
			{
				case 1:
					try {
						editaNome(Integer.parseInt(recebeCampo("ID").trim()),recebeCampo("Novo Nome").trim());
					}
					catch (NumberFormatException e) {
						System.out.println("[*Formato de ID invalido*]");
					}
					mostraTitulo();
					select = listaOpcao();
					break;
				case 2:
					editaNome(recebeCampo("Nome Atual"),recebeCampo("Novo Nome"));
					mostraTitulo();
					select = listaOpcao();
					break;
				case 0:
					sair = true;
					break;
				default:
					System.out.println("[*Opcao Invalida*]");
					mostraTitulo();
					select = listaOpcao();
			}
		}
	}

}