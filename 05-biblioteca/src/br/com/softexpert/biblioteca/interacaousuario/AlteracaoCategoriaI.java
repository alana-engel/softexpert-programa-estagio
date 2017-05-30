package br.com.softexpert.biblioteca.interacaousuario;

import javax.swing.JOptionPane;
import br.com.softexpert.biblioteca.operacoesregistros.OperacoesDadosCategoriaEmMemoria;
import br.com.softexpert.biblioteca.registros.Categoria;

public class AlteracaoCategoriaI{
	private OperacoesDadosCategoriaEmMemoria operacoesCategoria= new OperacoesDadosCategoriaEmMemoria();

	public void altera(){
		Categoria c = new Categoria();
		int posicao =operacoesCategoria.retornaPosicao();
		String dCategoria=(JOptionPane.showInputDialog("Digite a categoria: "));
		c=operacoesCategoria.busca(dCategoria);
		boolean achou=operacoesCategoria.achou();
		if (!achou){
			JOptionPane.showMessageDialog(null,"Não foi possível encontrar a categoria.");
		}
		else{
			String categoria="";
			categoria=JOptionPane.showInputDialog("Digite a nova categoria do livro: ");
			c.setDescricao(categoria);
			if(operacoesCategoria.altera(c, posicao)){
				JOptionPane.showMessageDialog(null, "Categoria alterada.");
			}else{
				JOptionPane.showMessageDialog(null, "O campo Categoria deve ser preenchido.");
				altera();
			}
		}
	}








}
