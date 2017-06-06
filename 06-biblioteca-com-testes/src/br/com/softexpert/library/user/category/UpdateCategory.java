package br.com.softexpert.library.user.category;

import javax.swing.JOptionPane;

import br.com.softexpert.library.entity.Category;
import br.com.softexpert.library.interfaces.Categories;
import br.com.softexpert.library.operations.memory.CategoriesInMemory;

public class UpdateCategory{
	private Categories categories= new CategoriesInMemory();

	public void update(){
		Category category = new Category();
		String description=(JOptionPane.showInputDialog("Digite a categoria: "));
		int exist = categories.checkIfCategoryExists(description);
		if (exist==-1){
			JOptionPane.showMessageDialog(null,"Não foi possível encontrar o autor.");
		}
		else{
			description=JOptionPane.showInputDialog("Digite a nova descrição da categoria: ");
			category.setDescription(description);
			if(categories.update(category, exist)){
				JOptionPane.showMessageDialog(null, "Categoria alterada.");
			}else{
				JOptionPane.showMessageDialog(null, "O campo Categoria deve ser preenchido.");
				update();
			}
		}
	}}


