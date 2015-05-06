package crud.view;

import java.util.Scanner;

import crud.controller.Usuarios;
import execoes.ValidationException;

public class MenuCadastraUsuario extends View {
	
	private final Usuarios control;
	
	MenuCadastraUsuario(Scanner scan, Usuarios control, String titulo) {
		super(scan);
		this.control = control;
		this.titulo = titulo;
	}
	
	private String cadastra() {
		String entrada = new String();
		String delimiter = " ,";
		String entPerfil = new String();
		try {
			entrada = entrada.concat(recebeCampoDeUsuario("Nome*").trim() + delimiter);
			entrada = entrada.concat(recebeCampoDeUsuario("CPF*").trim() + delimiter);
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
		System.out.println(entrada);
		return control.cadastra(entrada);
	}
	
	public void init() {
		mostraTitulo();
		System.out.println("[*"+ cadastra() + "*]");
	}
	
}
