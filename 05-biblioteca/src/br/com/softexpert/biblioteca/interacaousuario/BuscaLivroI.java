package br.com.softexpert.biblioteca.interacaousuario;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.softexpert.biblioteca.operacoesregistros.OperacoesDadosLivrosEmMemoria;
import br.com.softexpert.biblioteca.registros.Livro;


public class BuscaLivroI{
	private OperacoesDadosLivrosEmMemoria operacoesLivro= new OperacoesDadosLivrosEmMemoria();

	public void buscaLivroCod(){
		Livro livro =new Livro();
		int n=Integer.parseInt(JOptionPane.showInputDialog("Digite o código do livro: "));
		livro=operacoesLivro.buscaCod(n);
		boolean achou=operacoesLivro.achou();
		if (!achou){
			JOptionPane.showMessageDialog(null,"Não foi possível encontrar o livro.");
		}
		else{
			JOptionPane.showMessageDialog(null, livro);
		}
	}
	public void buscaLivroTitulo(){
		String titulo=(JOptionPane.showInputDialog("Digite o titulo do livro: "));
		List<Livro> listaTitulo = new ArrayList<Livro>();
		listaTitulo=operacoesLivro.buscaTitulo(titulo);
		boolean achou=operacoesLivro.achou();
		if (!achou){
			JOptionPane.showMessageDialog(null,"Não foi possível encontrar o livro.");
		}
		else{
			String imp="";
			for (int i=0;i<listaTitulo.size();i++){
				Livro livro = new Livro();
				livro=listaTitulo.get(i);
				imp=imp+livro;
			}
			JOptionPane.showMessageDialog(null, imp);
		}
	}
	public void buscaLivroCategoria(){
		String dcategoria=(JOptionPane.showInputDialog("Digite a categoria do livro: "));
		List<Livro> listaCategoria = new ArrayList<Livro>();
		listaCategoria=operacoesLivro.buscaCategoria(dcategoria);
		boolean achou=operacoesLivro.achou();
		if (!achou){
			JOptionPane.showMessageDialog(null,"Não foi possível encontrar o livro.");
		}
		else{
			String imp="";
			for (int i=0;i<listaCategoria.size();i++){
				Livro livro = new Livro();
				livro=listaCategoria.get(i);
				imp=imp+livro;
			}
			JOptionPane.showMessageDialog(null, imp);
		}
	}
	public void buscaLivroAutor(){
		String n=(JOptionPane.showInputDialog("Digite o autor do livro: "));
		List<Livro> listaPorAutor = new ArrayList<>();
		listaPorAutor=operacoesLivro.buscaAutor(n);
		String imp=new String();
		boolean achou=operacoesLivro.achou();
		if (!achou){
			JOptionPane.showMessageDialog(null,"Não foi possível encontrar o livro.");
		}else{
			for (int cont=0;cont<listaPorAutor.size();cont++){
				Livro livro = new Livro();
				livro=listaPorAutor.get(cont);
				imp=imp+livro;

			}
			JOptionPane.showMessageDialog(null, imp);
		}
	}
}
