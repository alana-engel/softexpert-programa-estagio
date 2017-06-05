package br.com.softexpert.library.user.book;

import javax.swing.JOptionPane;

public class Pages {
	public String getQuantityOfAuthors(){
		String pages=JOptionPane.showInputDialog("Digite a quantidade de autores: ");
		if(pages.isEmpty()){
			JOptionPane.showMessageDialog(null, "Preencha a quantidade de autores.");
		}
		return pages;
	}
}
