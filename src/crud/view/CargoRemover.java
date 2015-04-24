package crud.view;

import java.util.Scanner;

import crud.controller.Cargos;

public class CargoRemover extends View {
	
	private final Cargos control;
	
	CargoRemover(Scanner scan, Cargos control) {
		super(scan);
		this.control = control;
		titulo = "REMOVER";
	}

}
