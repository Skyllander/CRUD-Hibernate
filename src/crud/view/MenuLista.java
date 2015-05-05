package crud.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import crud.controller.Controller;
import crud.model.Cargo;
import crud.model.Perfil;
import crud.model.Usuario;

public class MenuLista<E> extends View
{
	private final Controller<E> control;
	private List<Integer> consultaID;
	private List<String> consultaN;

	MenuLista(Scanner scan, Controller<E> control, String titulo) {
		super(scan);
		consultaN = new ArrayList<String>();
		consultaID = new ArrayList<Integer>();
		this.control = control;
		this.titulo = titulo;
	}
	
	@SuppressWarnings("unchecked")
	private void setAtributos(List<E> obj) {
		if (!obj.isEmpty()) {
			if(obj.get(0).getClass().equals(Perfil.class)) {
				List<Perfil> perfis = (List<Perfil>) obj;
				for (Perfil perfil : perfis) {
					consultaID.add(perfil.id);
					consultaN.add(perfil.nome);
				}
			}
			else if(obj.get(0).getClass().equals(Usuario.class)) {
				List<Usuario> perfis = (List<Usuario>) obj;
				for (Usuario perfil : perfis) {
					consultaID.add(perfil.id);
					consultaN.add(perfil.nome);
				}
			}
			else {
				List<Cargo> cargos = (List<Cargo>) obj;
				for (Cargo cargo : cargos) {
					consultaID.add(cargo.id);
					consultaN.add(cargo.nome);
				}
			}
		}
	}
	
	private void limpaAtributos() {
		consultaID.clear();
		consultaN.clear();
	}
	
	private void lista() {
		setAtributos(control.listaOrdenadoPorNome());
		printMark();
		if (!consultaID.isEmpty()) {
			for(int i = 0; i < consultaID.size(); ++i) {
				System.out.println("ID: " + consultaID.get(i) + " | Nome: " + consultaN.get(i));
				printMark();
			}
		}
		else {
			System.out.println("[*Tabela vazia*]");
			printMark();
		}
		limpaAtributos();
	}
	
	public void init() {
		mostraTitulo();
		lista();
	}
	
}