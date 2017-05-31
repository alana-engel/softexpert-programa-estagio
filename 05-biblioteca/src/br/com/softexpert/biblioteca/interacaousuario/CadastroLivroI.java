package br.com.softexpert.biblioteca.interacaousuario;


import javax.swing.JOptionPane;

import br.com.softexpert.biblioteca.operacoesregistros.OperacoesDadosLivrosEmMemoria;
import br.com.softexpert.biblioteca.outrasoperacoes.Data;
import br.com.softexpert.biblioteca.registros.Livro;


public class CadastroLivroI{
	private Data operacoesData = new Data();
	private OperacoesDadosLivrosEmMemoria operacoesLivro= new OperacoesDadosLivrosEmMemoria();
	private RecebeQnt qnt = new RecebeQnt();
	public void cadastra(){
		String data;
		boolean dataT=false;
		Livro livro = new Livro();
		livro.setTitulo(JOptionPane.showInputDialog("Digite o titulo do livro: "));
		livro.setResumo(JOptionPane.showInputDialog("Digite o resumo do livro: "));
		String pag=JOptionPane.showInputDialog("Digite a quantidade de p�ginas: ");
		if(pag.isEmpty()){
			livro.setQntPaginas(0);
		}else{
			livro.setQntPaginas(Integer.parseInt(pag));
		}
		livro.setLocal(JOptionPane.showInputDialog("Digite o Local:"));
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
		String q = qnt.recebeQntAutores();
		if(q.isEmpty()){
			do{
				q = qnt.recebeQntAutores();
			}while(q.isEmpty());
		}
		//String nomeAutor=(JOptionPane.showInputDialog("Digite o nome do autor: "));
		//int existe=operacoesAutor.verificaSeAutorExisteRetornaPosicao(nomeAutor);
		livro.setLista(operacoesLivro.adicionaAutoresAoLivro(q));
		livro.setCategoria(operacoesLivro.adicionaCategoria());
		if(operacoesLivro.cadastra(livro)){
			JOptionPane.showMessageDialog(null, "Livro cadastrado.");
		}else{
			JOptionPane.showMessageDialog(null, "Livro n�o cadastrado, os campos Titulo e Local devem ser preenchidos.");
		}
	}

}
