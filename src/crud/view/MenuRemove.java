package crud.view;

import java.util.Scanner;
import crud.controller.Controller;

public class MenuRemove<E> extends View {

	private final Controller<E> control;

	MenuRemove(Scanner scan, Controller<E> control, String titulo) {
		super(scan);
		this.control = control;
		opcoes.add("Buscar ID");
		opcoes.add("Buscar Nome");
		this.titulo = titulo;
	}

	private <T>void remove(T tag) {
		System.out.println("[*" + control.remove(tag) + "*]");
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
					remove(Integer.parseInt(recebeCampo("ID")));
				}
				catch (NumberFormatException e) {
					System.out.println("[*Formato de ID invalido*]");
				}
				mostraTitulo();
				select = listaOpcao();
				break;
			case 2:
				remove(recebeCampo("Nome"));
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