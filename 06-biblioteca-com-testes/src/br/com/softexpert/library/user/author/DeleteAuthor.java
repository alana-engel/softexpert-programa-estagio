package br.com.softexpert.library.user.author;

import javax.swing.JOptionPane;

import br.com.softexpert.library.entity.Author;
import br.com.softexpert.library.interfaces.Operations;
import br.com.softexpert.library.operations.memory.AuthorsInMemory;

public class DeleteAuthor{
	private Operations<Author> authors= new AuthorsInMemory();

	public void delete(){
		String name=(JOptionPane.showInputDialog("Digite o nome do autor a ser removido."));
		if(authors.delete(name)){
			JOptionPane.showMessageDialog(null,"autor removido.");
		}else{
			JOptionPane.showMessageDialog(null,"Não foi possível remover o autor.");
		}
	}
}
