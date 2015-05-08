package crud.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import crud.controller.Usuarios;

public class MenuEditaUsuario extends View {
	
	private final Usuarios control;
	
	MenuEditaUsuario(Scanner scan, Usuarios control, String titulo) {
		super(scan);
		this.control = control;
		opcoes.add("Buscar ID");
		opcoes.add("Buscar Nome");
		this.titulo = titulo;
	}
	
	private <T>void edita(T tag, List<String> entrada) {
		System.out.println("[*" + control.edita(tag, entrada) + "*]");
	}
	
	private List<String> recebe() {
		List<String> entrada = new ArrayList<String>();
		
		entrada.add(recebeCampoDeUsuario("Nome*").trim());
		entrada.add(recebeCampoDeUsuario("CPF*").trim());
		System.out.println("-> Formato: Dia/Mes/Ano");
		entrada.add(recebeCampoDeUsuario("Data de Nascimento*").trim());
		entrada.add(recebeCampoDeUsuario("Sexo").trim());
		entrada.add(recebeCampoDeUsuario("Cargo*").trim());
		System.out.println("-> Deixe em branco para finalizar o cadastro");
		
		boolean sair = false;
		String novoPerfil = "";
		while(!sair) {
			novoPerfil = recebeCampoDeUsuario("Perfil").trim();
			if (!novoPerfil.isEmpty()) {
				entrada.add(novoPerfil);
			}
			else {
				sair = true;
			}
		}
		
		return entrada;
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
					Integer id = -1;
					List<String> entrada = new ArrayList<String>();
					
					try {
						id = Integer.parseInt(recebeCampo("ID").trim());
					}
					catch (NumberFormatException e) {
						System.out.println("[*Formato de ID invalido*]");
					}
					
					entrada = recebe();
					
					edita(id, entrada);
					mostraTitulo();
					select = listaOpcao();
					break;
				case 2:
					edita(recebeCampo("Nome Atual"), recebe());
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