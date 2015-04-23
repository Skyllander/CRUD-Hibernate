package crud.view;

import java.util.ArrayList;
import java.util.Scanner;

public class View {

	public void mostraTitulo(String s) {
		String mark = new String();
		for (int i = 0; i < s.length() ; ++i) mark = mark.concat("=");
		System.out.println(mark);
		System.out.println(s);
		System.out.println(mark + "\n");
	}
	
	public String recebeCampo(String s, Scanner user_input) {
		System.out.print(s + ": ");
		String input = user_input.nextLine();
		System.out.println("");
		return input;
	}
	
	public ArrayList<String> recebeCampo(ArrayList<String> sA, Scanner user_input) {
		ArrayList<String> entradas = new ArrayList<String>();
		for (String s : sA) {
			System.out.print(s + ": ");
			entradas.add(user_input.nextLine());
		}
		System.out.println("");
		return entradas;
	}
	
	public void listaOpcoes(ArrayList<String> sA) {
		int op = 1;
		for (String s : sA) {
			System.out.println(op + " - " + s);
			++op;
		}
		System.out.println("");
	}
	
	public void listaOpcoes(String s) {
		System.out.println("1" + " - " + s);
		System.out.println("");
	}
	
	
}
