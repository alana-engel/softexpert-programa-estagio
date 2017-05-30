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
			JOptionPane.showMessageDialog(null,"N�o foi poss�vel encontrar a categoria.");
		}
		else{
			imp=imp+"C�digo da Categoria: "+c.getCodCategoria()+"\n"+"Descri��o da Categoria: "+c.getDescricao();
			JOptionPane.showMessageDialog(null, imp);
		}
	}
}
