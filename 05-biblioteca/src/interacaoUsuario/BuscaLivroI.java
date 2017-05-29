package interacaoUsuario;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import biblioteca.Livro;
import operacoes.OperacoesDadosLivrosEmMemoria;


public class BuscaLivroI{
	OperacoesDadosLivrosEmMemoria buscaLivro= new OperacoesDadosLivrosEmMemoria();
	RecebeData data = new RecebeData();
	public void buscaLivroCod(){
		Livro livro =new Livro();
		int n=Integer.parseInt(JOptionPane.showInputDialog("Digite o código do livro: "));
		livro=buscaLivro.buscaCod(n);
		boolean achou=buscaLivro.achou();
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
		listaTitulo=buscaLivro.buscaTitulo(titulo);
		boolean achou=buscaLivro.achou();
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
		listaCategoria=buscaLivro.buscaCategoria(dcategoria);
		boolean achou=buscaLivro.achou();
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
		listaPorAutor=buscaLivro.buscaAutor(n);
		String imp=new String();
		boolean achou=buscaLivro.achou();
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
