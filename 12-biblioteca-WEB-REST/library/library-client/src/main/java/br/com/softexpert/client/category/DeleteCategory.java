package br.com.softexpert.client.category;
import javax.swing.JOptionPane;

import br.com.softexpert.library.entity.Category;
import br.com.softexpert.library.interfaces.Operations;
import br.com.softexpert.library.operations.db.hibernate.CategoryJPA;
import br.com.softexpert.library.operations.db.hibernate.Manager;



public class DeleteCategory{
	private Operations<Category> categories= new CategoryJPA(Manager.getEntityManager());
	public void delete(){
		int id=Integer.parseInt(JOptionPane.showInputDialog("Digite o código da categoria: "));
		if(categories.delete(id)){
			JOptionPane.showMessageDialog(null,"Categoria removida.");
		}else{
			JOptionPane.showMessageDialog(null,"Não foi possível remover a categoria.");
		}
	}
}
