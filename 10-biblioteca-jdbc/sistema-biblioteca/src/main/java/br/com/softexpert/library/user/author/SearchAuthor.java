package br.com.softexpert.library.user.author;
import javax.swing.JOptionPane;
import br.com.softexpert.library.interfaces.Authors;
import br.com.softexpert.library.operations.memory.AuthorsInMemory;

public class SearchAuthor{
	private Authors authors= new AuthorsInMemory();
	public void search(){
		String name=(JOptionPane.showInputDialog("Digite o nome do autor: "));
			try {
				JOptionPane.showMessageDialog(null, authors.search(name));
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,"N�o foi poss�vel encontrar o autor.");
				e.printStackTrace();
			}
	}
}
