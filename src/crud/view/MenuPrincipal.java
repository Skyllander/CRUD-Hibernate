package crud.view;

import java.util.Scanner;

import crud.controller.*;

public class MenuPrincipal extends View {


	MenuCargos mCargos;
	private final Cargos controlC;
	
	MenuPrincipal(Scanner scan) {
		super(scan);
		controlC = new Cargos();
		opcoes.add("Cargos");
		mCargos = new MenuCargos(user_scan, controlC);
		titulo = "MENU-PRINCIPAL";
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
					mCargos.init();
					mostraTitulo();
					select = listaOpcao();
					break;
				case 0:
					System.out.println("Exit");
					controlC.close();
					sair = true;
					break;
				default:
					System.out.println("[Opcao Invalida]");
					mostraTitulo();
					select = listaOpcao();
			}
		}
	}
	
}
