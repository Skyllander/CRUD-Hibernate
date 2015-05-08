package crud.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import crud.controller.Usuarios;

public class MenuCadastraUsuario extends View {
	
	private final Usuarios control;
	
	MenuCadastraUsuario(Scanner scan, Usuarios control, String titulo) {
		super(scan);
		this.control = control;
		this.titulo = titulo;
	}
	
	private String cadastra() {
		
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
		
		return control.cadastra(entrada);
	}
	
	public void init() {
		mostraTitulo();
		System.out.println("[*"+ cadastra() + "*]");
	}
	
}
