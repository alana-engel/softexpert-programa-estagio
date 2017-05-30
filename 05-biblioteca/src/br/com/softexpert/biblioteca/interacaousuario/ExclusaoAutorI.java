package br.com.softexpert.biblioteca.interacaousuario;

import javax.swing.JOptionPane;

import br.com.softexpert.biblioteca.interfaces.Acoes;
import br.com.softexpert.biblioteca.operacoesregistros.OperacoesDadosAutorEmMemoria;
import br.com.softexpert.biblioteca.registros.Autor;

public class ExclusaoAutorI{
	private Acoes<Autor> operacoesAutor= new OperacoesDadosAutorEmMemoria();

	public void exclui(){
		String nAutor=(JOptionPane.showInputDialog("Digite o nome do autor a ser removido."));
		if(operacoesAutor.remove(nAutor)){
			JOptionPane.showMessageDialog(null,"autor removido.");
		}else{
			JOptionPane.showMessageDialog(null,"Não foi possível remover o autor.");
		}
	}
}
