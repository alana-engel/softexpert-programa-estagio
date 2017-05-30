package br.com.softexpert.biblioteca.interacaousuario;

import javax.swing.JOptionPane;

import br.com.softexpert.biblioteca.operacoesregistros.OperacoesDadosLivrosEmMemoria;
import br.com.softexpert.biblioteca.outrasoperacoes.Data;
import br.com.softexpert.biblioteca.registros.Livro;

public class AlteracaoLivroI{

	private OperacoesDadosLivrosEmMemoria operacoesLivro= new OperacoesDadosLivrosEmMemoria();
	private RecebeQnt qnt = new RecebeQnt();
	private Data operacoesData = new Data();
	
	public void altera(){
		Livro livro =new Livro();
		int cod=Integer.parseInt(JOptionPane.showInputDialog("Digite o código do livro: "));
		livro=operacoesLivro.buscaCod(cod);
		int posicao =operacoesLivro.retornaPosicao();
		boolean achou=operacoesLivro.achou();
		if (!achou){
			JOptionPane.showMessageDialog(null,"Não foi possível encontrar o livro.");
		}
		else{
			livro.setTitulo(JOptionPane.showInputDialog("Digite o titulo do livro: "));
			livro.setResumo(JOptionPane.showInputDialog("Digite o resumo do livro: "));
			String pag=JOptionPane.showInputDialog("Digite a quantidade de páginas: ");
			if(pag.isEmpty()){
				livro.setQntPaginas(0);
			}else{
				livro.setQntPaginas(Integer.parseInt(pag));
			}
			livro.setLocal(JOptionPane.showInputDialog("Digite o Local:"));
			String data = JOptionPane.showInputDialog("Digite a data de aquisição:");
			if(data.isEmpty())
				data = "01/01/01";
			if(operacoesData.ComparaData(data)){
				livro.setDataDeAquisicao(operacoesData.retornaDataTransformada(data));
			}else{
				JOptionPane.showMessageDialog(null,"A data de aquisição não pode ser superior a data atual.");
			}
			
			String q = qnt.recebeQntAutores();
			if(q.isEmpty()){
				do{
					q = qnt.recebeQntAutores();
				}while(q.isEmpty());
			}
			livro.setLista(operacoesLivro.adicionaAutoresAoLivro(q));
			livro.setCategoria(operacoesLivro.adicionaCategoria());
			if(operacoesLivro.altera(livro, posicao)==true){
				JOptionPane.showMessageDialog(null, "Livro alterado.");
			}else{
				JOptionPane.showMessageDialog(null, "Livro não alterado, os campos Titulo e Local devem ser preenchidos.");
			}
		}
	}
}
