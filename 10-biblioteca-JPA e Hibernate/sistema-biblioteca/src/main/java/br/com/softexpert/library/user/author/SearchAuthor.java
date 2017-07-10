package br.com.softexpert.library.user.author;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import br.com.softexpert.library.entity.Author;
import br.com.softexpert.library.interfaces.Operations;
import br.com.softexpert.library.operations.db.hibernate.AuthorJPA;



public class SearchAuthor{
	EntityManagerFactory factory = Persistence.
			createEntityManagerFactory("library");
	EntityManager manager = factory.createEntityManager();
	private Operations<Author> authors= new AuthorJPA(manager);
	public void search(){
		String name=(JOptionPane.showInputDialog("Digite o nome do autor: "));
		try {
			JOptionPane.showMessageDialog(null, authors.search(name));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Não foi possível encontrar o autor.");
			e.printStackTrace();
		}
	}
}
