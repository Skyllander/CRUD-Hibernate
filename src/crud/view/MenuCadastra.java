package crud.view;

import java.util.Scanner;

import crud.controller.Controller;

public class MenuCadastra<T> extends View {
	
	private final Controller<T> control;
	
	MenuCadastra(Scanner scan, Controller<T> control, String titulo) {
		super(scan);
		this.control = control;
		this.titulo = titulo;
	}
	
	private String cadastra() {
		return control.cadastra(recebeCampo("Nome*").trim());
	}
	
	public void init() {
		mostraTitulo();
		System.out.println("[*"+ cadastra() + "*]");
	}
	
}
