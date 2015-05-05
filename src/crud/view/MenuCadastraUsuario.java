package crud.view;

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
		/*String[] entrada = new String[6];
		entrada[0] = recebeCampo("Nome*").trim();
		entrada[1] = recebeCampo("CPF*").trim();
		entrada[2] = recebeCampo("Data de Nascimento").trim();
		entrada[3] = recebeCampo("Sexo").trim();
		entrada[4] = recebeCampo("Cargo").trim();
		entrada[5] = recebeCampo("Perfil").trim();*/
		String entrada = new String();
		entrada = entrada.concat(recebeCampo("Nome*").trim() + "_");
		entrada = entrada.concat(recebeCampo("CPF*").trim() + "_");
		entrada = entrada.concat(recebeCampo("Data de Nascimento").trim() + "_");
		entrada = entrada.concat(recebeCampo("Sexo").trim() + "_");
		entrada = entrada.concat(recebeCampo("Cargo").trim() + "_");
		entrada = entrada.concat(recebeCampo("Perfil").trim() + "_");
		System.out.println(entrada);
		return control.cadastra(entrada);
	}
	
	public void init() {
		mostraTitulo();
		System.out.println("[*"+ cadastra() + "*]");
	}
	
}
