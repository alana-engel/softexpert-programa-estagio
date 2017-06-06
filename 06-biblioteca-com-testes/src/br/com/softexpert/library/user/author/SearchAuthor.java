package br.com.softexpert.library.user.author;

import javax.swing.JOptionPane;

import br.com.softexpert.library.interfaces.Authors;
import br.com.softexpert.library.operations.memory.AuthorsInMemory;

public class SearchAuthor{
	private Authors authors= new AuthorsInMemory();
	public void search(){
		String name=(JOptionPane.showInputDialog("Digite o nome do autor: "));
		int exist=authors.checkIfAuthorExists(name);
		if (exist == -1){
			JOptionPane.showMessageDialog(null,"Não foi possível encontrar o autor.");
		}
		else{
			JOptionPane.showMessageDialog(null, authors.search(name));
		}
	}
}
