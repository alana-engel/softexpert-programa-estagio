package br.com.softexpert.library.user.book;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import br.com.softexpert.library.operations.db.hibernate.BookJPA;

public class DeleteBook{
	EntityManagerFactory factory = Persistence.
			createEntityManagerFactory("library");
	EntityManager manager = factory.createEntityManager();
	private BookJPA books= new BookJPA(manager);

	public void delete(){
		String title=(JOptionPane.showInputDialog("Digite titulo do livro a ser removido.")); 
		if(books.delete(title)){
			JOptionPane.showMessageDialog(null,"livro removido.");
		}else{
			JOptionPane.showMessageDialog(null,"Não foi possível remover o livro.");
		}
	}
}
