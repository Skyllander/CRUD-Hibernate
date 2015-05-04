package crud.view;

import java.util.Scanner;

public class MenuPrincipal extends View {

	private final MenuCargo mCargo;
	private final MenuPerfil mPerfil;
	
	MenuPrincipal(Scanner scan) {
		super(scan);
		opcoes.add("Cargos");
		opcoes.add("Perfis");
		mCargo = new MenuCargo(user_scan);
		mPerfil = new MenuPerfil(user_scan);
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
					mCargo.init();
					mostraTitulo();
					select = listaOpcao();
					break;
				case 2:
					mPerfil.init();
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
