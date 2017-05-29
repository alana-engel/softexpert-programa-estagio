package interacaoUsuario;

import javax.swing.JOptionPane;

import biblioteca.Autor;
import interfaces.Acoes;
import operacoes.OperacoesDadosAutorEmMemoria;

public class ExclusaoAutorI{
	Acoes<Autor> gravaAutor= new OperacoesDadosAutorEmMemoria();

	public void exclui(){
		String nAutor=(JOptionPane.showInputDialog("Digite o nome do autor a ser removido."));
		if(gravaAutor.remove(nAutor)){
			JOptionPane.showMessageDialog(null,"autor removido.");
		}else{
			JOptionPane.showMessageDialog(null,"Não foi possível remover o autor.");
		}
	}
}
