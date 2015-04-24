package crud.view;

import java.util.Scanner;

import crud.controller.Cargos;

public class CargoCadastrar extends View {
	
	private final Cargos control;
	
	CargoCadastrar(Scanner scan, Cargos control) {
		super(scan);
		this.control = control;
		opcoes.add("Novo Cargo");
		titulo = "CADASTRO";
	}
	
	private void cadastra() {
		control.cadastra(recebeCampo("Nome"));
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
					cadastra();
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
