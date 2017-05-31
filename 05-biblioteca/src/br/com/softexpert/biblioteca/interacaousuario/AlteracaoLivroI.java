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
		int cod=Integer.parseInt(JOptionPane.showInputDialog("Digite o c�digo do livro: "));
		int existe = operacoesLivro.verificaSeLivroExisteRetornaPosicao(cod);
		if (existe==-1){
			JOptionPane.showMessageDialog(null,"N�o foi poss�vel encontrar o Livro.");
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
			recebeDataAquisicao(livro);
			String q = qnt.recebeQntAutores();
			if(q.isEmpty()){
				do{
					q = qnt.recebeQntAutores();
				}while(q.isEmpty());
			}
			livro.setLista(operacoesLivro.adicionaAutoresAoLivro(q));
			livro.setCategoria(operacoesLivro.adicionaCategoria());
			if(operacoesLivro.altera(livro, existe)==true){
				JOptionPane.showMessageDialog(null, "Livro alterado.");
			}else{
				JOptionPane.showMessageDialog(null, "Livro n�o alterado, os campos Titulo e Local devem ser preenchidos.");
			}
		}
	}

	private void recebeDataAquisicao(Livro livro) {
		String data;
		boolean dataT;
		do{
			data = JOptionPane.showInputDialog("Digite a data de aquisi��o no formato dd/MM/yyyy:");
			if(data.isEmpty()){
				data = "01/01/01";
				dataT=true;
			}else{
				dataT=operacoesData.dataTransformada(data);
			}
		}while(dataT==false);
		if(operacoesData.ComparaData(operacoesData.retornaDataTransformada(data))){
			livro.setDataDeAquisicao(operacoesData.retornaDataTransformada(data));
		}else{
			JOptionPane.showMessageDialog(null,"A data de aquisi��o n�o pode ser superior a data atual.");
		}
	}
}
