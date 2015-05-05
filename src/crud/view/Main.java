package crud.view;

import java.util.Scanner;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.validation.Validation;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import crud.model.JPAUtil;

public class Main {

	public static void main(String args[]) {
		/*EntityManagerFactory entityManagerFactory = 
				entityManagerFactory = Persistence.createEntityManagerFactory("sistemacrud");
		entityManagerFactory.createEntityManager();*/
		Logger.getRootLogger().setLevel(Level.OFF);
		Scanner user_scan = new Scanner(System.in);
		MenuPrincipal menu = new MenuPrincipal(user_scan);
		menu.init();
		user_scan.close();
	}
	
}