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
			JOptionPane.showMessageDialog(null,"N�o foi poss�vel encontrar a categoria");
		}
		else{

			imp=imp+"C�digo da Categoria: "+c.getCodCategoria()+"\n"+"Descri��o da Categoria: "+c.getDescricao();
			JOptionPane.showMessageDialog(null, imp);
		}
	}
}
