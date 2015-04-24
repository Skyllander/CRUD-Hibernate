package crud.view;

import java.util.Scanner;

import crud.controller.Cargos;

public class CargoEditar extends View {
	
	private final Cargos control;
	
	CargoEditar(Scanner scan, Cargos control) {
		super(scan);
		this.control = control;
		titulo = "EDICAO";
	}

}
