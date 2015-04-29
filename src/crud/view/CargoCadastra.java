package crud.view;

import java.util.Scanner;

import crud.controller.Cargos;

public class CargoCadastra extends View {
	
	private final Cargos control;
	
	CargoCadastra(Scanner scan, Cargos control) {
		super(scan);
		this.control = control;
		titulo = "CARGOS-CADASTRAR";
	}
	
	private String cadastra() {
		return control.cadastra(recebeCampo("Nome*"));
	}
	
	public void init() {
		mostraTitulo();
		System.out.println("[*"+ cadastra() + "*]");
	}
	
}
