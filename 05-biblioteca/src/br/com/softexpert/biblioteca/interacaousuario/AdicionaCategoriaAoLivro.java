package br.com.softexpert.biblioteca.interacaousuario;

import javax.swing.JOptionPane;

public class AdicionaCategoriaAoLivro {
	
	public String retornaDescricaoCategoria(){
		String dCategoria=(JOptionPane.showInputDialog("Digite a categoria do livro: "));
		if (dCategoria.isEmpty()) {
			JOptionPane.showMessageDialog(null, "O campo Categoria deve ser preenchido.");
		}
		return dCategoria;
	}
	public void retornaMensagemCategoriaNaoEncontrado(){
		JOptionPane.showMessageDialog(null,"N�o foi poss�vel encontrar a categoria, uma nova categoria ser� cadastrada.");
	}
}
