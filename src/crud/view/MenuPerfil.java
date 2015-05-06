package crud.view;

import java.util.Scanner;
import crud.controller.Perfis;
import crud.model.Perfil;

public class MenuPerfil extends View {
	
	private final MenuCadastra<Perfil> cadastro;
	private final MenuEdita<Perfil> edicao;
	private final MenuLista<Perfil> listagem;
	private final MenuRemove<Perfil> remocao;
	
	private final Perfis control;
	
	MenuPerfil(Scanner scan) {
		super(scan);
		control = new Perfis();
		cadastro = new MenuCadastra<Perfil>(scan, control,"PERFIL-CADASTRAR");
		edicao = new MenuEdita<Perfil>(scan, control,"PERFIL-EDITAR");
		listagem = new MenuLista<Perfil>(scan, control,"PERFIL-LISTAR");
		remocao = new MenuRemove<Perfil>(scan, control,"PERFIL-REMOVER");
		opcoes.add("Cadastrar");
		opcoes.add("Listar");
		opcoes.add("Editar");
		opcoes.add("Remover");
		titulo = "MENU-PERFIL";
	}
	
	public void init() {
		mostraTitulo();
		Integer select = -1;
		select = listaOpcao();
		boolean sair = false;
		while (!sair) {
			switch(select)
			{
				case 1:
					cadastro.init();
					mostraTitulo();
					select = listaOpcao();
					break;
				case 2:
					listagem.init();
					mostraTitulo();
					select = listaOpcao();
					break;
				case 3:
					edicao.init();
					mostraTitulo();
					select = listaOpcao();
					break;
				case 4:
					remocao.init();
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
		}
	}
	
}
