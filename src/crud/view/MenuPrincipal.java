package crud.view;

import java.util.Scanner;

public class MenuPrincipal extends View {

	MenuCargos mCargos;
	
	MenuPrincipal(Scanner scan) {
		super(scan);
		opcoes.add("Cargos");
		mCargos = new MenuCargos(user_scan);
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
					System.out.println("[*Exit*]");
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
