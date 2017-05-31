package br.com.softexpert.biblioteca.interacaousuario;

import javax.swing.JOptionPane;

import br.com.softexpert.biblioteca.operacoesregistros.OperacoesDadosCategoriaEmMemoria;
import br.com.softexpert.biblioteca.registros.Categoria;

public class AlteracaoCategoriaI{
	private OperacoesDadosCategoriaEmMemoria operacoesCategoria= new OperacoesDadosCategoriaEmMemoria();

	public void altera(){
		Categoria c = new Categoria();
		String dCategoria=(JOptionPane.showInputDialog("Digite a categoria: "));
		int existe = operacoesCategoria.verificaSeCategoriaExisteRetornaPosicao(dCategoria);
		if (existe==-1){
			JOptionPane.showMessageDialog(null,"Não foi possível encontrar o autor.");
		}
		else{
			String categoria=JOptionPane.showInputDialog("Digite a nova descrição da categoria: ");
			c.setDescricao(categoria);
			if(operacoesCategoria.altera(c, existe)){
				JOptionPane.showMessageDialog(null, "Categoria alterada.");
			}else{
				JOptionPane.showMessageDialog(null, "O campo Categoria deve ser preenchido.");
				altera();
			}
		}
	}








}








