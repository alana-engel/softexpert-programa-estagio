package br.com.softexpert.library.user.category;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import br.com.softexpert.library.entity.Category;
import br.com.softexpert.library.interfaces.Operations;
import br.com.softexpert.library.operations.db.hibernate.CategoryJPA;



public class DeleteCategory{
	EntityManagerFactory factory = Persistence.
			createEntityManagerFactory("library");
	EntityManager manager = factory.createEntityManager();
	private Operations<Category> categories= new CategoryJPA(manager);
	public void delete(){
		String description=(JOptionPane.showInputDialog("Digite a categoria a ser removida.")); 
		if(categories.delete(description)){
			JOptionPane.showMessageDialog(null,"Categoria removida.");
		}else{
			JOptionPane.showMessageDialog(null,"Não foi possível remover a categoria.");
		}
	}
}
