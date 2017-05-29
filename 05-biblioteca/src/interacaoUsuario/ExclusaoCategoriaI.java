package interacaoUsuario;

import javax.swing.JOptionPane;

import biblioteca.Categoria;
import interfaces.Acoes;
import operacoes.OperacoesDadosCategoriaEmMemoria;


public class ExclusaoCategoriaI{
	Acoes<Categoria> gravaCategoria= new OperacoesDadosCategoriaEmMemoria();
	public void exclui(){
		String dcategoria=(JOptionPane.showInputDialog("Digite a categoria a ser removida.")); 
		if(gravaCategoria.remove(dcategoria)){
			JOptionPane.showMessageDialog(null,"Categoria removida.");
		}else{
			JOptionPane.showMessageDialog(null,"N�o foi poss�vel remover a categoria.");
		}
	}
}
