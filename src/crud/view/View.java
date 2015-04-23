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
	
	public int listaOpcao(ArrayList<String> sA, Scanner user_input) {
		int op = 1;
		printMark();
		for (String s : sA) {
			System.out.println(op + " - " + s);
			++op;
		}
		fimOp();
		String in = user_input.nextLine();
		Integer select = -1;
		try{
			select = Integer.parseInt(in);
		}
		catch (NumberFormatException e) {
		}
		System.out.println("");
		return select;
	}
	
	public int listaOpcao(String s, Scanner user_input) {
		printMark();
		System.out.println("1" + " - " + s);
		fimOp();
		String in = user_input.nextLine();
		Integer select = -1;
		try{
			select = Integer.parseInt(in);
		}
		catch (NumberFormatException e) {
		}
		/*Integer entrada = Integer.parseInt(in);
		if (entrada < 0 || entrada > 1) {
			System.out.println("Opcao Invalida\n");
			return "-1";
		}
		else {
			System.out.println("");
			return in;
		}*/
		System.out.println("");
		return select;
	}
	
	private void printMark() {
		System.out.println("--------------------");
	}
	
	private void fimOp() {
		System.out.println("0 - Sair");
		printMark();
		System.out.println("");
		System.out.print("Entrada: ");
	}
	
	
}
