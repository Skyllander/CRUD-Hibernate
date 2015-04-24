package crud.view;

import java.util.Scanner;

import crud.controller.Cargos;

public class CargoCadastrar extends View {
	
	private final Cargos control;
	
	CargoCadastrar(Scanner scan, Cargos control) {
		super(scan);
		this.control = control;
		titulo = "CARGOS-CADASTRAR";
	}
	
	private boolean cadastra() {
		return control.cadastra(recebeCampo("Nome*"));
	}
	
	public void init() {
		mostraTitulo();
		if (cadastra()) System.out.println("Inserido com sucesso");
		else {
			System.out.println("[*Os nomes dos cargos devem ser exclusivos*]");
			System.out.println("[*O campo de nome e obrigatorio*]");
		}
	}
	
}
