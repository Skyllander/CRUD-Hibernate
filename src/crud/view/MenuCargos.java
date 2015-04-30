package crud.view;

import java.util.Scanner;

import crud.controller.Cargos;

public class MenuCargos extends View {
	
	private final CargoCadastra cadastro;
	private final CargoEdita edicao;
	private final CargoLista listagem;
	private final CargoRemove remocao;
	
	private final Cargos control;
	
	MenuCargos(Scanner scan) {
		super(scan);
		control = new Cargos();
		cadastro = new CargoCadastra(scan, control);
		edicao = new CargoEdita(scan, control);
		listagem = new CargoLista(scan, control);
		remocao = new CargoRemove(scan, control);
		opcoes.add("Cadastrar");
		opcoes.add("Listar");
		opcoes.add("Editar");
		opcoes.add("Remover");
		titulo = "MENU-CARGOS";
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
