package interacaoUsuario;

import javax.swing.JOptionPane;

import biblioteca.Livro;
import operacoes.OperacoesDadosLivrosEmMemoria;



public class AlteracaoLivroI{
	RecebeData data = new RecebeData();
	OperacoesDadosLivrosEmMemoria gravaLivro= new OperacoesDadosLivrosEmMemoria();
	
	public void altera(){
		Livro livro =new Livro();
		int cod=Integer.parseInt(JOptionPane.showInputDialog("Digite o c�digo do livro: "));
		livro=gravaLivro.buscaCod(cod);
		int posicao =gravaLivro.retornaPosicao();
		boolean achou=gravaLivro.achou();
		if (!achou){
			JOptionPane.showMessageDialog(null,"N�o foi poss�vel encontrar o livro.");
		}
		else{
			livro.setTitulo(JOptionPane.showInputDialog("Digite o titulo do livro: "));
			livro.setResumo(JOptionPane.showInputDialog("Digite o resumo do livro: "));
			String pag=JOptionPane.showInputDialog("Digite a quantidade de p�ginas: ");
			if(pag.isEmpty()){
				livro.setQntPaginas(0);
			}else{
				livro.setQntPaginas(Integer.parseInt(pag));
			}
			livro.setLocal(JOptionPane.showInputDialog("Digite o Local:"));
			livro.setDataDeAquisicao(data.recebeDataLivro());
			livro.setLista(gravaLivro.adicionaAutoresAoLivro());
			livro.setCategoria(gravaLivro.adicionaCategoria());
			if(gravaLivro.altera(livro, posicao)==true){
				JOptionPane.showMessageDialog(null, "Livro Alterado.");
			}else{
				JOptionPane.showMessageDialog(null, "Livro n�o alterado, os campos Titulo e Local devem ser preenchidos.");
			}
		}
	}
	
}
