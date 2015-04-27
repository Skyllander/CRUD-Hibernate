package crud.view;

import java.util.Scanner;

import crud.controller.Cargos;

public class CargoRemover extends View {
	
	private final Cargos control;
	
	CargoRemover(Scanner scan, Cargos control) {
		super(scan);
		this.control = control;
		opcoes.add("Buscar ID");
		opcoes.add("Buscar Nome");
		titulo = "CARGOS-REMOVER";
	}
	
	private <T>void removeNome(T id) {
		if (control.removeNome(id)) {
			System.out.println("[*Entrada removida*]");
		}
		else System.out.println("[*Nao encontrado*]");
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
					try {
						removeNome(Integer.parseInt(recebeCampo("ID")));
					}
					catch (NumberFormatException e) {
						System.out.println("[*Formato de ID invalido*]");
					}
					mostraTitulo();
					select = listaOpcao();
					break;
				case 2:
					removeNome(recebeCampo("Nome"));
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