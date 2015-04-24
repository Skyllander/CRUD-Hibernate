package crud.view;

import java.util.List;
import java.util.Scanner;
import crud.controller.Cargos;

public class CargoListar extends View
{
	private final Cargos control;

	CargoListar(Scanner scan, Cargos control) {
		super(scan);
		this.control = control;
		titulo = "CARGOS-LISTAR";
	}
	
	private void lista() {
		List<String> consulta = control.lista();
		printMark();
		if (!consulta.isEmpty()) {
			for(String s : consulta) {
				System.out.println(s);
				printMark();
			}
		}
		else {
			System.out.println("[*Tabela vazia*]");
			printMark();
		}
	}
	
	public void init() {
		mostraTitulo();
		lista();
	}
	
}