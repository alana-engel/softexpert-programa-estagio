package br.com.softexpert.library.user.book;

import javax.swing.JOptionPane;
import br.com.softexpert.library.operations.db.hibernate.BookJPA;
import br.com.softexpert.library.operations.db.hibernate.Manager;

public class DeleteBook{
	private BookJPA books= new BookJPA(Manager.getEntityManager());

	public void delete(){
		String title=(JOptionPane.showInputDialog("Digite titulo do livro a ser removido.")); 
		if(books.delete(title)){
			JOptionPane.showMessageDialog(null,"livro removido.");
		}else{
			JOptionPane.showMessageDialog(null,"Não foi possível remover o livro.");
		}
	}
}
