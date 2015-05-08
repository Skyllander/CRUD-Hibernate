package crud.view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import crud.controller.Usuarios;
import crud.model.Cargo;
import crud.model.Perfil;
import crud.model.Sexo;
import crud.model.Usuario;

public class MenuListaUsuario extends View
{
	private final Usuarios control;
	private List<Integer> consultaID;
	private List<String> consultaN;
	private List<String> consultaCPF;
	private List<Calendar> consultaNascimento;
	private List<Calendar> consultaDataCadastro;
	private List<Sexo> consultaSexo;
	private List<Cargo> consultaCargo;
	private List<List<Perfil>> consultaPerfil;

	MenuListaUsuario(Scanner scan, Usuarios control, String titulo) {
		super(scan);
		consultaN = new ArrayList<String>();
		consultaID = new ArrayList<Integer>();
		consultaCPF = new ArrayList<String>();
		consultaNascimento = new ArrayList<Calendar>();
		consultaDataCadastro = new ArrayList<Calendar>();
		consultaSexo = new ArrayList<Sexo>();
		consultaCargo = new ArrayList<Cargo>();
		consultaPerfil = new ArrayList<List<Perfil>>();
		opcoes.add("Nome");
		opcoes.add("Cargo");
		opcoes.add("Perfil");
		this.control = control;
		this.titulo = titulo;
	}

	private void setAtributos(List<Usuario> usuarios) {
		if (!usuarios.isEmpty()) {
			int last;
			for (Usuario usuario : usuarios) {
				consultaID.add(usuario.id);
				consultaN.add(usuario.nome);
				consultaCPF.add(usuario.cpf);
				consultaNascimento.add(usuario.dataNascimento);
				consultaDataCadastro.add(usuario.dataCadastro);
				consultaSexo.add(usuario.sexo);
				consultaCargo.add(usuario.cargo);
				consultaPerfil.add(new ArrayList<Perfil>());
				last = consultaPerfil.size() - 1;
				if (!usuario.perfis.isEmpty()) {
					for(Perfil p : usuario.perfis) {
						consultaPerfil.get(last).add(p);
					}
				}
			}
		}
	}

	private void limpaAtributos() {
		consultaID.clear();
		consultaN.clear();
		consultaCPF.clear();
		consultaNascimento.clear();
		consultaDataCadastro.clear();
		consultaSexo.clear();
		consultaCargo.clear();
		consultaPerfil.clear();
	}
	
	private void listaFiltro(String nome, String cargo, String perfil) {
		List<Usuario> usuarios = control.listaFiltro(nome, cargo, perfil);
		lista(usuarios);
	}

	private void lista(List<Usuario> usuarios) {
		setAtributos(usuarios);
		printMark();
		if (!consultaID.isEmpty()) {
			if (control.getClass().equals(Usuarios.class)) {
				for(int i = 0; i < consultaID.size(); ++i) {
					SimpleDateFormat style = new SimpleDateFormat("dd/MM/yyyy");
					System.out.println("ID: " + consultaID.get(i));
					System.out.println("Nome: " + consultaN.get(i));
					System.out.println("CPF: " + consultaCPF.get(i));
					System.out.println("Sexo: " + consultaSexo.get(i));
					System.out.println("Cargo: " + consultaCargo.get(i).nome);
					System.out.println("Nascimento: " + style.format(consultaNascimento.get(i).getTime()));
					System.out.println("Cadastro: " + style.format(consultaDataCadastro.get(i).getTime()));
					System.out.print("Perfis: ");
					if (!consultaPerfil.get(i).isEmpty())
						for (Perfil p : consultaPerfil.get(i)) {
							System.out.print(p.nome + "|");
						}
					else System.out.print("|");
					System.out.println();
					printMark();
				}
			}
			else {
				for(int i = 0; i < consultaID.size(); ++i) {
					System.out.println("ID: " + consultaID.get(i) + " | Nome: " + consultaN.get(i));
					printMark();
				}
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
		System.out.println("-> Filtros:");
		listaFiltro(recebeCampo("Nome"),recebeCampo("Cargo"),recebeCampo("Perfil"));
		/*
		Integer select = -1;
		select = listaOpcao();
		boolean sair = false;
		while (!sair) {
			switch(select)
			{
				case 1:
					listaFiltro(recebeCampo("Nome"),recebeCampo("Cargo"),recebeCampo("Perfil"));
					//listaNome(recebeCampo("Nome"));
					mostraTitulo();
					select = listaOpcao();
					break;
				case 2:
					listaCargo(recebeCampo("Cargo"));
					mostraTitulo();
					select = listaOpcao();
					break;
				case 3:
					mostraTitulo();
					select = listaOpcao();
					break;
				case 0:
					sair = true;
					break;
				default:
					System.out.println("[*Opcao Invalida*]");
					mostraTitulo();
					select = listaOpcao();
			}
		}*/
	}

}