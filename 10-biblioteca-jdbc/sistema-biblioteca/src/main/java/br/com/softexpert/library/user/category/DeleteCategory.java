package br.com.softexpert.library.user.category;

import javax.swing.JOptionPane;

import br.com.softexpert.library.entity.Category;
import br.com.softexpert.library.interfaces.Operations;
import br.com.softexpert.library.operations.memory.CategoriesInMemory;



public class DeleteCategory{
	private Operations<Category> categories= new CategoriesInMemory();
	public void delete(){
		String description=(JOptionPane.showInputDialog("Digite a categoria a ser removida.")); 
		if(categories.delete(description)){
			JOptionPane.showMessageDialog(null,"Categoria removida.");
		}else{
			JOptionPane.showMessageDialog(null,"Não foi possível remover a categoria.");
		}
	}
}
