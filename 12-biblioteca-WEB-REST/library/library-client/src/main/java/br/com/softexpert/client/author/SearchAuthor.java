package br.com.softexpert.client.author;
import javax.swing.JOptionPane;
import br.com.softexpert.library.entity.Author;
import br.com.softexpert.library.interfaces.Operations;
import br.com.softexpert.library.operations.db.hibernate.AuthorJPA;
import br.com.softexpert.library.operations.db.hibernate.Manager;



public class SearchAuthor{
	private Operations<Author> authors= new AuthorJPA(Manager.getEntityManager());
	public void search(){
		int id=Integer.parseInt(JOptionPane.showInputDialog("Digite o código do autor: "));
		try {
			JOptionPane.showMessageDialog(null, authors.search(id));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Não foi possível encontrar o autor.");
		}
	}
}
