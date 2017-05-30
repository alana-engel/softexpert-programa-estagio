package br.com.softexpert.biblioteca.interacaousuario;

import javax.swing.JOptionPane;
public class AdicionaAutorAoLivro {

	private String nomeAutor;
	public void retornaMensagemAutorNaoEncontrado(){
		JOptionPane.showMessageDialog(null,"Não foi possível encontrar o autor, um novo autor será cadastrado.");

	}
	public String recebeNomeAutor(){
		nomeAutor=(JOptionPane.showInputDialog("Digite o nome do autor: "));
		if (nomeAutor.isEmpty()){
			JOptionPane.showMessageDialog(null, "O campo Nome do autor deve ser preenchido.");
			recebeNomeAutor();
		}
		return nomeAutor;
	}
	public String retornaNomeAutor(){
		return nomeAutor;
	}
	
}	
