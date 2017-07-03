package br.com.softexpert.library.user.category;

import javax.swing.JOptionPane;

import br.com.softexpert.library.entity.Category;
import br.com.softexpert.library.interfaces.Categories;
import br.com.softexpert.library.operations.memory.CategoriesInMemory;


public class UpdateCategory{
	private Categories categories= new CategoriesInMemory();

	public void update(){
		Category category = new Category();
		Category newCategory = new Category();
		String description=(JOptionPane.showInputDialog("Digite a categoria: "));
		try {
			category = categories.search(description);
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Categoria não encontrada.");
			update();
			e1.printStackTrace();
			return;
		}
		description=JOptionPane.showInputDialog("Digite a nova descrição da categoria: ");
		newCategory.setDescription(description);
		try {
			categories.update(category, newCategory);
			JOptionPane.showMessageDialog(null, "Categoria alterada.");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "O campo Categoria deve ser preenchido.");
			update();
			e.printStackTrace();
		}
	}
}


