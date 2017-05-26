package interacaoUsuario;

import javax.swing.JOptionPane;

import biblioteca.Categoria;
import interfaces.Acoes;
import operacoes.OperacoesDadosCategoriaEmMemoria;




public class AlteracaoCategoriaI{
	OperacoesDadosCategoriaEmMemoria buscaCategoria= new OperacoesDadosCategoriaEmMemoria();
	Acoes<Categoria> gravaCategoria= new OperacoesDadosCategoriaEmMemoria();
	public void altera(){
		Categoria c = new Categoria();
		int posicao =buscaCategoria.retornaPosicao();
		String dCategoria=(JOptionPane.showInputDialog("Digite a categoria: "));
		c=buscaCategoria.busca(dCategoria);
		boolean achou=buscaCategoria.achou();
		
		if (!achou){
			JOptionPane.showMessageDialog(null,"Não foi possível encontrar a categoria");
		}
		else{
			String categoria="";
			categoria=JOptionPane.showInputDialog("Digite a nova categoria do livro: ");
			c.setDescricao(categoria);
			if(gravaCategoria.altera(c, posicao)==true){
				JOptionPane.showMessageDialog(null, "Categoria Alterada.");
			}else{
				JOptionPane.showMessageDialog(null, "O campo Categoria deve ser preenchido.");
				altera();
			}
			
		}
	}
	
	
	
	
	
	
	
	
}
