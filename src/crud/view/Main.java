package crud.view;

import java.util.Scanner;

import crud.model.JPAUtil;

public class Main {

	public static void main(String args[]) {
		Scanner user_scan = new Scanner(System.in);
		MenuPrincipal menu = new MenuPrincipal(user_scan);
		menu.init();
		user_scan.close();
		JPAUtil.close();
	}
	
}