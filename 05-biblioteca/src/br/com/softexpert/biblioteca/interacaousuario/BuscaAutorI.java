package br.com.softexpert.biblioteca.interacaousuario;

import javax.swing.JOptionPane;

import br.com.softexpert.biblioteca.operacoesregistros.OperacoesDadosAutorEmMemoria;
import br.com.softexpert.biblioteca.registros.Autor;

public class BuscaAutorI{
	private OperacoesDadosAutorEmMemoria operacoesAutor= new OperacoesDadosAutorEmMemoria();
	public void busca(){
		Autor autor = new Autor();
		String nAutor=(JOptionPane.showInputDialog("Digite o nome do autor: "));
		autor=operacoesAutor.busca(nAutor);
		boolean achou=operacoesAutor.achou();
		if (!achou){
			JOptionPane.showMessageDialog(null,"Não foi possível encontrar o autor.");
		}
		else{
			JOptionPane.showMessageDialog(null, autor);
		}
	}
}
