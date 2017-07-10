package br.com.softexpert.library.user.author;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import br.com.softexpert.library.entity.Author;
import br.com.softexpert.library.interfaces.Operations;
import br.com.softexpert.library.operations.db.hibernate.AuthorJPA;

public class DeleteAuthor{
	EntityManagerFactory factory = Persistence.
			createEntityManagerFactory("library");
	EntityManager manager = factory.createEntityManager();
	private Operations<Author> authors= new AuthorJPA(manager);

	public void delete(){
		String name=(JOptionPane.showInputDialog("Digite o nome do autor a ser removido."));
		if(authors.delete(name)){
			JOptionPane.showMessageDialog(null,"Autor removido.");
		}else{
			JOptionPane.showMessageDialog(null,"Não foi possível remover o autor.");
		}
	}
}
