package br.com.softexpert.biblioteca.interacaousuario;

import javax.swing.JOptionPane;

import br.com.softexpert.biblioteca.interfaces.Acoes;
import br.com.softexpert.biblioteca.operacoesregistros.OperacoesDadosCategoriaEmMemoria;
import br.com.softexpert.biblioteca.registros.Categoria;


public class ExclusaoCategoriaI{
	private Acoes<Categoria> operacoesCategoria= new OperacoesDadosCategoriaEmMemoria();
	public void exclui(){
		String dcategoria=(JOptionPane.showInputDialog("Digite a categoria a ser removida.")); 
		if(operacoesCategoria.remove(dcategoria)){
			JOptionPane.showMessageDialog(null,"Categoria removida.");
		}else{
			JOptionPane.showMessageDialog(null,"Não foi possível remover a categoria.");
		}
	}
}
