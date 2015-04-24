package crud.view;

import java.util.Scanner;

import crud.controller.Cargos;

public class CargoListar extends View
{
	private final Cargos control;

	CargoListar(Scanner scan, Cargos control) {
		super(scan);
		this.control = control;
		titulo = "LISTAGEM";
	}
	
}
