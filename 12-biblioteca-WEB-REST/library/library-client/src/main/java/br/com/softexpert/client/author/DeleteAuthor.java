package br.com.softexpert.client.author;

import javax.swing.JOptionPane;

import br.com.softexpert.library.entity.Author;
import br.com.softexpert.library.interfaces.Operations;
import br.com.softexpert.library.operations.db.hibernate.AuthorJPA;
import br.com.softexpert.library.operations.db.hibernate.Manager;

public class DeleteAuthor{
	private Operations<Author> authors= new AuthorJPA(Manager.getEntityManager());

	public void delete(){
		int id=Integer.parseInt(JOptionPane.showInputDialog("Digite o código do autor: "));
		if(authors.delete(id)){
			JOptionPane.showMessageDialog(null,"Autor removido.");
		}else{
			JOptionPane.showMessageDialog(null,"Não foi possível remover o autor.");
		}
	}
}
