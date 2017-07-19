package br.com.softexpert.client.book;

import javax.swing.JOptionPane;
import br.com.softexpert.library.operations.db.hibernate.BookJPA;
import br.com.softexpert.library.operations.db.hibernate.Manager;

public class DeleteBook{
	private BookJPA books= new BookJPA(Manager.getEntityManager());

	public void delete(){
		int n=Integer.parseInt(JOptionPane.showInputDialog("Digite o código do livro: "));
		if(books.delete(n)){
			JOptionPane.showMessageDialog(null,"livro removido.");
		}else{
			JOptionPane.showMessageDialog(null,"Não foi possível remover o livro.");
		}
	}
}
