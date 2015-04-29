package crud.view;

import java.util.List;
import java.util.Scanner;
import crud.controller.Cargos;

public class CargoLista extends View
{
	private final Cargos control;

	CargoLista(Scanner scan, Cargos control) {
		super(scan);
		this.control = control;
		titulo = "CARGOS-LISTAR";
	}
	
	private void lista() {
		List<String> consultaN = control.listaNome();
		List<Integer> consultaID = control.listaID();
		printMark();
		if (!consultaID.isEmpty()) {
			for(int i = 0; i < consultaID.size(); ++i) {
				System.out.println("ID: " + consultaID.get(i) + " | Nome: " + consultaN.get(i));
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