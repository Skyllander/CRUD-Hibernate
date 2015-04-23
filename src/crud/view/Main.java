package crud.view;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String args[]) {
		Scanner user_scan = new Scanner(System.in);
		View v = new View();
		v.mostraTitulo("CARGOS");
		//v.recebeCampo("Nome", user_scan);
		ArrayList<String> ok = new ArrayList<String>();
		ok.add("CPF");
		ok.add("ID");
		ok.add("Novo");
		ok.add("Old");
		//v.recebeCampo("New", user_scan);
		//v.recebeCampo(ok, user_scan);
		v.listaOpcoes(ok);
		v.listaOpcoes("ok");
		user_scan.close();
	}
	
}
