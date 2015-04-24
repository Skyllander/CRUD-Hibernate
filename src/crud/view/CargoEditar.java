package crud.view;

import java.util.Scanner;

import crud.controller.Cargos;

public class CargoEditar extends View {
	
	private final Cargos control;
	
	CargoEditar(Scanner scan, Cargos control) {
		super(scan);
		this.control = control;
		opcoes.add("Buscar ID");
		opcoes.add("Buscar Nome");
		titulo = "CARGOS-EDITAR";
	}
	
	private void editaNome(int id, String nome) {
		if (control.editaNome(id, nome)) {
			System.out.println("[*Alteracao feita*]");
		}
		else System.out.println("[*ID nao econtrada*]");
	}
	
	private void editaNome(String nNome, String vNome) {
		if (control.editaNome(nNome, vNome)) {
			System.out.println("[*Alteracao feita*]");
		}
		else System.out.println("[*Nome nao encontrado*]");
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
					//cadastro.init();
					editaNome(Integer.parseInt(recebeCampo("ID")),recebeCampo("Novo Nome"));
					mostraTitulo();
					select = listaOpcao();
					break;
				case 2:
					//listagem.init();
					editaNome(recebeCampo("Nome Atual"),recebeCampo("Novo Nome"));
					mostraTitulo();
					select = listaOpcao();
					break;
				case 0:
					System.out.println("Exit");
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