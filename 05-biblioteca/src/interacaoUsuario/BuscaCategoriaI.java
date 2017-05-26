package interacaoUsuario;

import javax.swing.JOptionPane;

import biblioteca.Categoria;
import operacoes.OperacoesDadosCategoriaEmMemoria;


public class BuscaCategoriaI{
	OperacoesDadosCategoriaEmMemoria buscaCategoria= new OperacoesDadosCategoriaEmMemoria();
	public void busca(){
		Categoria c = new Categoria();
		String imp=new String();
		String dCategoria=(JOptionPane.showInputDialog("Digite a categoria: "));
		c=buscaCategoria.busca(dCategoria);
		boolean achou=buscaCategoria.achou();
		
		if (!achou){
			JOptionPane.showMessageDialog(null,"Não foi possível encontrar a categoria");
		}
		else{

			imp=imp+"Código da Categoria: "+c.getCodCategoria()+"\n"+"Descrição da Categoria: "+c.getDescricao();
			JOptionPane.showMessageDialog(null, imp);
		}
	}
}
