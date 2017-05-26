package interacaoUsuario;

import javax.swing.JOptionPane;

import biblioteca.Livro;
import interfaces.Acoes;
import operacoes.OperacoesDadosLivrosEmMemoria;
public class ExclusaoLivroI{
	Acoes<Livro> gravaLivro= new OperacoesDadosLivrosEmMemoria();
	public void exclui(){
		String TLivro=(JOptionPane.showInputDialog("Digite titulo do livro a ser removido.")); 
		if(gravaLivro.remove(TLivro)==true){
			JOptionPane.showMessageDialog(null,"livro removido.");
		}else{
			JOptionPane.showMessageDialog(null,"Não foi possível remover o livro.");
		}
	}
}
