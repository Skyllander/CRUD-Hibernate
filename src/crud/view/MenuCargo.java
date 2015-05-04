package crud.view;

import java.util.Scanner;

import crud.controller.Cargos;
import crud.controller.Controller;
import crud.model.Cargo;

public class MenuCargo extends View {
	
	private final MenuCadastra<Cargo> cadastro;
	private final MenuEdita<Cargo> edicao;
	private final MenuLista<Cargo> listagem;
	private final MenuRemove<Cargo> remocao;
	
	private final Controller<Cargo> control;
	
	MenuCargo(Scanner scan) {
		super(scan);
		control = new Cargos();
		cadastro = new MenuCadastra<Cargo>(scan, control, "CARGO-CADASTRAR");
		edicao = new MenuEdita<Cargo>(scan, control, "CARGO-EDITAR");
		listagem = new MenuLista<Cargo>(scan, control, "CARGO-LISTAR");
		remocao = new MenuRemove<Cargo>(scan, control, "CARGO-REMOVER");
		opcoes.add("Cadastrar");
		opcoes.add("Listar");
		opcoes.add("Editar");
		opcoes.add("Remover");
		titulo = "MENU-CARGO";
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
