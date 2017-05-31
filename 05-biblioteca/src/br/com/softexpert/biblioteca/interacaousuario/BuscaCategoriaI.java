package br.com.softexpert.biblioteca.interacaousuario;

import javax.swing.JOptionPane;

import br.com.softexpert.biblioteca.operacoesregistros.OperacoesDadosCategoriaEmMemoria;

public class BuscaCategoriaI{
	private OperacoesDadosCategoriaEmMemoria operacoesCategoria= new OperacoesDadosCategoriaEmMemoria();

	public void busca(){
		String dCategoria=(JOptionPane.showInputDialog("Digite a categoria: "));
		int existe=operacoesCategoria.verificaSeCategoriaExisteRetornaPosicao(dCategoria);
		if (existe == -1){
			JOptionPane.showMessageDialog(null,"Não foi possível encontrar o autor.");
		}
		else{
			JOptionPane.showMessageDialog(null, operacoesCategoria.busca(dCategoria));
		}
	}
}
