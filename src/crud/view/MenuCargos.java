package crud.view;

import java.util.Scanner;

import crud.controller.Cargos;

public class MenuCargos extends View {
	
	CargoCadastrar cadastro;
	CargoEditar edicao;
	CargoListar listagem;
	CargoRemover remocao;
	
	private final Cargos control;
	
	MenuCargos(Scanner scan, Cargos control) {
		super(scan);
		this.control = control;
		cadastro = new CargoCadastrar(scan, control);
		edicao = new CargoEditar(scan, control);
		listagem = new CargoListar(scan, control);
		remocao = new CargoRemover(scan, control);
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
					mostraTitulo();
					select = listaOpcao();
					break;
				case 3:
					mostraTitulo();
					select = listaOpcao();
					break;
				case 4:
					mostraTitulo();
					select = listaOpcao();
					break;
				case 0:
					System.out.println("Exit");
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
