package menuBiblioteca;

import javax.swing.JOptionPane;

import interacaoUsuario.AlteracaoAutorI;
import interacaoUsuario.AlteracaoCategoriaI;
import interacaoUsuario.AlteracaoLivroI;
import interacaoUsuario.BuscaAutorI;
import interacaoUsuario.BuscaCategoriaI;
import interacaoUsuario.CadastroAutorI;
import interacaoUsuario.CadastroCategoriaI;
import interacaoUsuario.CadastroLivroI;
import interacaoUsuario.ExclusaoAutorI;
import interacaoUsuario.ExclusaoCategoriaI;
import interacaoUsuario.ExclusaoLivroI;
import outrasOperacoes.GravaEmArquivo;

public class SubMenu1 {
	AlteracaoLivroI alteraLivro = new AlteracaoLivroI();
	CadastroLivroI cadastroLivro = new CadastroLivroI();
	AlteracaoAutorI cAutor  = new AlteracaoAutorI();
	AlteracaoCategoriaI op = new AlteracaoCategoriaI();
	GravaEmArquivo grava = new GravaEmArquivo();
	ExclusaoAutorI exclusaoAutor= new ExclusaoAutorI();
	CadastroAutorI cadastroAutor = new CadastroAutorI();
	SubMenu2 menu = new SubMenu2();
	BuscaAutorI buscaAutor = new BuscaAutorI();
	ExclusaoLivroI exclusaoLivro = new ExclusaoLivroI();
	CadastroCategoriaI cadastroCategoria = new CadastroCategoriaI();
	BuscaCategoriaI buscaCategoria = new BuscaCategoriaI();
	ExclusaoCategoriaI exclusaoCategoria = new ExclusaoCategoriaI();
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
			case 2:op.altera();break;
			case 3:cAutor.altera();break;
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
