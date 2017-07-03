package br.com.softexpert.library.user.author;
import javax.swing.JOptionPane;

import br.com.softexpert.library.entity.Author;

import br.com.softexpert.library.interfaces.Operations;
import br.com.softexpert.library.operations.db.AuthorDao;



public class SearchAuthor{
	private Operations<Author> authors= new AuthorDao();
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
