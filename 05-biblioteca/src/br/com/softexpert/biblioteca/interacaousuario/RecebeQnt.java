package br.com.softexpert.biblioteca.interacaousuario;

import javax.swing.JOptionPane;

public class RecebeQnt {
	public String recebeQntAutores(){
		String qntdade=JOptionPane.showInputDialog("Digite a quantidade de autores: ");
		if(qntdade.isEmpty()){
			JOptionPane.showMessageDialog(null, "Preencha a quantidade de autores.");
		}
		return qntdade;
	}
}
