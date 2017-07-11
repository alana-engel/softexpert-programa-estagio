package br.com.softexpert.library.user.author;
import javax.swing.JOptionPane;
import br.com.softexpert.library.entity.Author;
import br.com.softexpert.library.interfaces.Operations;
import br.com.softexpert.library.operations.db.hibernate.AuthorJPA;
import br.com.softexpert.library.operations.db.hibernate.Manager;



public class SearchAuthor{
	private Operations<Author> authors= new AuthorJPA(Manager.getEntityManager());
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
