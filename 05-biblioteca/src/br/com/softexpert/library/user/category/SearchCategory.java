package br.com.softexpert.library.user.category;

import javax.swing.JOptionPane;

import br.com.softexpert.library.interfaces.Categories;
import br.com.softexpert.library.operations.memory.CategoriesInMemory;

public class SearchCategory{
	private Categories categories= new CategoriesInMemory();

	public void search(){
		String description=(JOptionPane.showInputDialog("Digite a categoria: "));
		int exist=categories.checkIfCategoryExists(description);
		if (exist == -1){
			JOptionPane.showMessageDialog(null,"Não foi possível encontrar a categoria.");
		}
		else{
			JOptionPane.showMessageDialog(null, categories.search(description));
		}
	}
}
