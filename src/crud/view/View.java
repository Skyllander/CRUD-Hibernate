package crud.view;

import java.util.ArrayList;
import java.util.Scanner;

public class View {
	
	Scanner user_scan;
	String titulo;
	ArrayList<String> opcoes;
	ArrayList<String> campos;
	
	View (Scanner scan) {
		user_scan = scan;
		opcoes = new ArrayList<String>();
		campos = new ArrayList<String>();
		titulo = "NAO-DEFINIDO";
	}
	public void mostraTitulo() {
		String mark = new String();
		for (int i = 0; i < titulo.length() ; ++i) mark = mark.concat("=");
		System.out.println(mark);
		System.out.println(titulo);
		System.out.println(mark + "\n");
	}
	
	public String recebeCampo(String s) {
		System.out.print(s + ": ");
		String input = user_scan.nextLine();
		System.out.println("");
		return input;
	}
	
	public ArrayList<String> recebeCampo() {
		ArrayList<String> entradas = new ArrayList<String>();
		for (String s : campos) {
			System.out.print(s + ": ");
			entradas.add(user_scan.nextLine());
		}
		System.out.println("");
		return entradas;
	}
	
	public int listaOpcao() {
		int op = 1;
		printMark();
		for (String s : opcoes) {
			System.out.println(op + " - " + s);
			++op;
		}
		fimOp();
		String in = user_scan.nextLine();
		Integer select = -1;
		try{
			select = Integer.parseInt(in);
		}
		catch (NumberFormatException e) {
		}
		System.out.println("");
		return select;
	}
	
	public int listaOpcao(String s) {
		printMark();
		System.out.println("1" + " - " + s);
		fimOp();
		String in = user_scan.nextLine();
		Integer select = -1;
		try{
			select = Integer.parseInt(in);
		}
		catch (NumberFormatException e) {
		}
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
