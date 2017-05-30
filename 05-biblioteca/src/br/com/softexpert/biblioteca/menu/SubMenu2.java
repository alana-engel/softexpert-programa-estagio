package br.com.softexpert.biblioteca.menu;

import javax.swing.JOptionPane;

import br.com.softexpert.biblioteca.interacaousuario.BuscaLivroI;
import br.com.softexpert.biblioteca.outrasoperacoes.Ordenacao;

public class SubMenu2 {
	private BuscaLivroI opcoesBuscaLivro = new BuscaLivroI();
	private Ordenacao ordenacao = new Ordenacao();
	public void menuBuscarLivro(){
		ordenacao.ordena();
		int opcao=0;
		do{
			opcao=Integer.parseInt(JOptionPane.showInputDialog(""
					+ "1-Buscar pelo Código\n"
					+ "2-Buscar pelo Título\n"
					+ "3-Buscar pela Categoria\n"
					+ "4-Buscar pelo Autor\n"
					+ "5-Voltar"));
			switch(opcao){
			case 1:opcoesBuscaLivro.buscaLivroCod();;break;
			case 2:opcoesBuscaLivro.buscaLivroTitulo();break;
			case 3:opcoesBuscaLivro.buscaLivroCategoria();break;
			case 4:opcoesBuscaLivro.buscaLivroAutor();break;
			}
		}while(opcao!=5);
	}
}
