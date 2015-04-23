package crud.view;

import java.util.ArrayList;
import java.util.Scanner;

import crud.controller.*;

public class MenuPrincipal extends View {

	Scanner user_scan;
	private final Cargos control;
	ArrayList<String> opcoes;
	
	MenuPrincipal(Scanner scan) {
		user_scan = scan;
		control = new Cargos();
		opcoes = new ArrayList<String>();
		opcoes.add("Cargos");
	}
	
	public void init() {
		mostraTitulo("MENU");
		Integer select = -1;
		select = listaOpcao(opcoes, user_scan);
		boolean valid = false;
		while (!valid) {
			switch(select)
			{
				case 1:
					System.out.println("Ok");
					valid = true;
					break;
				case 0:
					System.out.println("Exit");
					valid = true;
					break;
				default:
					System.out.println("Opcao Invalida");
					select = listaOpcao(opcoes, user_scan);
			}
		}
	}
	
}
