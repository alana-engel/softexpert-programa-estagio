package br.com.softexpert.biblioteca.interacaousuario;

import javax.swing.JOptionPane;

import br.com.softexpert.biblioteca.interfaces.Acoes;
import br.com.softexpert.biblioteca.operacoesregistros.OperacoesDadosLivrosEmMemoria;
import br.com.softexpert.biblioteca.registros.Livro;
public class ExclusaoLivroI{
	private Acoes<Livro> operacoesLivro= new OperacoesDadosLivrosEmMemoria();
	public void exclui(){
		String TLivro=(JOptionPane.showInputDialog("Digite titulo do livro a ser removido.")); 
		if(operacoesLivro.remove(TLivro)){
			JOptionPane.showMessageDialog(null,"livro removido.");
		}else{
			JOptionPane.showMessageDialog(null,"Não foi possível remover o livro.");
		}
	}
}
