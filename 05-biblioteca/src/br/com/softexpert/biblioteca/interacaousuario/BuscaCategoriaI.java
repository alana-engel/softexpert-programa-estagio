package br.com.softexpert.biblioteca.interacaousuario;

import javax.swing.JOptionPane;

import br.com.softexpert.biblioteca.operacoesregistros.OperacoesDadosCategoriaEmMemoria;
import br.com.softexpert.biblioteca.registros.Categoria;

public class BuscaCategoriaI{
	private OperacoesDadosCategoriaEmMemoria operacoesCategoria= new OperacoesDadosCategoriaEmMemoria();
	
	public void busca(){
		Categoria c = new Categoria();
		String imp=new String();
		String dCategoria=(JOptionPane.showInputDialog("Digite a categoria: "));
		c=operacoesCategoria.busca(dCategoria);
		boolean achou=operacoesCategoria.achou();
		if (!achou){
			JOptionPane.showMessageDialog(null,"Não foi possível encontrar a categoria.");
		}
		else{
			imp=imp+"Código da Categoria: "+c.getCodCategoria()+"\n"+"Descrição da Categoria: "+c.getDescricao();
			JOptionPane.showMessageDialog(null, imp);
		}
	}
}
