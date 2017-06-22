package br.com.softexpert.library.user.book;

import javax.swing.JOptionPane;

public class QAuthors {
	public String getQuantityOfAuthors(){
		String qAuthors=JOptionPane.showInputDialog("Digite a quantidade de autores: ");
		if(qAuthors.isEmpty()){
			JOptionPane.showMessageDialog(null, "Preencha a quantidade de autores.");
		}
		return qAuthors;
	}
}
