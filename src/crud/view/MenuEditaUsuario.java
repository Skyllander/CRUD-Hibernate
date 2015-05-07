package crud.view;

import java.util.Scanner;

import crud.controller.Usuarios;
import execoes.ValidationException;

public class MenuEditaUsuario extends View {
	
	private final Usuarios control;
	
	MenuEditaUsuario(Scanner scan, Usuarios control, String titulo) {
		super(scan);
		this.control = control;
		opcoes.add("Buscar ID");
		opcoes.add("Buscar Nome");
		this.titulo = titulo;
	}
	
	private <T>void edita(T tag, String entrada) {
		System.out.println("[*" + control.edita(tag, entrada) + "*]");
	}
	
	private String recebe() {
		String entrada = new String();
		
		String delimiter = " ,";
		String entPerfil = new String();
		try {
			entrada = entrada.concat(recebeCampoDeUsuario("Nome*").trim() + delimiter);
			entrada = entrada.concat(recebeCampoDeUsuario("CPF*").trim() + delimiter);
			System.out.println("-> Formato: Dia/Mes/Ano");
			entrada = entrada.concat(recebeCampoDeUsuario("Data de Nascimento").trim() + delimiter);
			entrada = entrada.concat(recebeCampoDeUsuario("Sexo").trim() + delimiter);
			entrada = entrada.concat(recebeCampoDeUsuario("Cargo*").trim() + delimiter);
			System.out.println("-> Deixe em branco para finalizar o cadastro");
			while (!entPerfil.equals(delimiter)) {
				entPerfil = recebeCampoDeUsuario("Perfil").trim() + delimiter;
				entrada = entrada.concat(entPerfil);
			}
		}
		catch (ValidationException e){
			return e.getMessage();
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
					String entrada = new String();
					
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