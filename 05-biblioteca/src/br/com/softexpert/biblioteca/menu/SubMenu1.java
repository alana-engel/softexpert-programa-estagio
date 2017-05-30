package br.com.softexpert.biblioteca.menu;

import javax.swing.JOptionPane;

import br.com.softexpert.biblioteca.interacaousuario.AlteracaoAutorI;
import br.com.softexpert.biblioteca.interacaousuario.AlteracaoCategoriaI;
import br.com.softexpert.biblioteca.interacaousuario.AlteracaoLivroI;
import br.com.softexpert.biblioteca.interacaousuario.BuscaAutorI;
import br.com.softexpert.biblioteca.interacaousuario.BuscaCategoriaI;
import br.com.softexpert.biblioteca.interacaousuario.CadastroAutorI;
import br.com.softexpert.biblioteca.interacaousuario.CadastroCategoriaI;
import br.com.softexpert.biblioteca.interacaousuario.CadastroLivroI;
import br.com.softexpert.biblioteca.interacaousuario.ExclusaoAutorI;
import br.com.softexpert.biblioteca.interacaousuario.ExclusaoCategoriaI;
import br.com.softexpert.biblioteca.interacaousuario.ExclusaoLivroI;

public class SubMenu1 {
	private AlteracaoLivroI alteraLivro = new AlteracaoLivroI();
	private AlteracaoAutorI alteraAutor  = new AlteracaoAutorI();
	private AlteracaoCategoriaI alteraCategoria = new AlteracaoCategoriaI();
	private CadastroLivroI cadastroLivro = new CadastroLivroI();
	private CadastroCategoriaI cadastroCategoria = new CadastroCategoriaI();
	private CadastroAutorI cadastroAutor = new CadastroAutorI();
	private ExclusaoLivroI exclusaoLivro = new ExclusaoLivroI();
	private ExclusaoAutorI exclusaoAutor= new ExclusaoAutorI();
	private ExclusaoCategoriaI exclusaoCategoria = new ExclusaoCategoriaI();
	private BuscaAutorI buscaAutor = new BuscaAutorI();
	private BuscaCategoriaI buscaCategoria = new BuscaCategoriaI();
	private SubMenu2 menu = new SubMenu2();
	
	
	public void menuCadastrar(){
		int opcao=0;
		do{
			opcao=Integer.parseInt(JOptionPane.showInputDialog(""
					+ "1-Cadastrar Livro\n"
					+ "2-Cadastrar Categoria\n"
					+ "3-Cadastrar Autor\n"
					+ "4-Voltar"));
			switch(opcao){
			case 1:cadastroLivro.cadastra();break;
			case 2:cadastroCategoria .cadastra();break;
			case 3:cadastroAutor.cadastra();break;
			}
		}while(opcao!=4);
	}

	public void menuAlterar(){
		int opcao;
		do{
			opcao=Integer.parseInt(JOptionPane.showInputDialog(""
					+ "1-Alterar dados do Livro\n"
					+ "2-Alterar dados da Categoria\n"
					+ "3-Alterar dados do Autor\n"
					+ "4-Voltar"));
			switch(opcao){
			case 1:alteraLivro.altera();break;
			case 2:alteraCategoria.altera();break;
			case 3:alteraAutor.altera();break;
			}
		}while(opcao!=4);
	}

	public void menuBuscar(){
		int opcao;
		do{
			opcao=Integer.parseInt(JOptionPane.showInputDialog(""
					+ "1-Buscar Livro\n"
					+ "2-Buscar Categoria\n"
					+ "3-Buscar Autor\n"
					+ "4-Voltar"));
			switch(opcao){
			case 1:menu.menuBuscarLivro();break;
			case 2:buscaCategoria.busca();break;
			case 3:buscaAutor.busca();break;
			}
		}while(opcao!=4);
	}

	public void menuExcluir(){
		int opcao;
		do{
			opcao=Integer.parseInt(JOptionPane.showInputDialog(""
					+ "1-Excluir Livro\n"
					+ "2-Excluir Categoria\n"
					+ "3-Excluir Autor\n"
					+ "4-Voltar"));
			switch(opcao){
			case 1:exclusaoLivro.exclui();break;
			case 2:exclusaoCategoria.exclui();;break;
			case 3:exclusaoAutor.exclui();;break;
			}
		}while(opcao!=4);
	}

}
