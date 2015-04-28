package crud.view;

import java.util.Scanner;

import crud.controller.Cargos;

public class MenuCargos extends View {
	
	private CargoCadastrar cadastro;
	private CargoEditar edicao;
	private CargoListar listagem;
	private CargoRemover remocao;
	
	private final Cargos control;
	
	MenuCargos(Scanner scan) {
		super(scan);
		control = new Cargos();
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
