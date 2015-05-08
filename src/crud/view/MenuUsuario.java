package crud.view;

import java.util.Scanner;
import crud.controller.Usuarios;

public class MenuUsuario extends View {
	
	private final MenuCadastraUsuario cadastro;
	private final MenuEditaUsuario edicao;
	private final MenuListaUsuario listagem;
	private final MenuRemoveUsuario remocao;
	private final Usuarios control;
	
	MenuUsuario(Scanner scan) {
		super(scan);
		control = new Usuarios();
		cadastro = new MenuCadastraUsuario(scan, control, "USUARIO-CADASTRAR");
		edicao = new MenuEditaUsuario(scan, control, "USUARIO-EDITAR");
		listagem = new MenuListaUsuario (scan, control, "USUARIO-LISTAR");
		remocao = new MenuRemoveUsuario(scan, control, "USUARIO-REMOVER");
		opcoes.add("Cadastrar");
		opcoes.add("Listar");
		opcoes.add("Editar");
		opcoes.add("Remover");
		titulo = "MENU-USUARIO";
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
					cadastro.init();
					mostraTitulo();
					select = listaOpcao();
					break;
				case 2:
					listagem.init();
					mostraTitulo();
					select = listaOpcao();
					break;
				case 3:
					edicao.init();
					mostraTitulo();
					select = listaOpcao();
					break;
				case 4:
					remocao.init();
					mostraTitulo();
					select = listaOpcao();
					break;
				case 0:
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
