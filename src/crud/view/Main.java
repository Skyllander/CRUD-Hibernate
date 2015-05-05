package crud.view;

import java.util.Scanner;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class Main {

	public static void main(String args[]) {
		Logger.getRootLogger().setLevel(Level.OFF);
		Scanner user_scan = new Scanner(System.in);
		MenuPrincipal menu = new MenuPrincipal(user_scan);
		menu.init();
		user_scan.close();
	}
	
}