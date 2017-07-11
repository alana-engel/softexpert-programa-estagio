package br.com.softexpert.library.user.category;

import javax.swing.JOptionPane;

import br.com.softexpert.library.entity.Category;
import br.com.softexpert.library.interfaces.Operations;
import br.com.softexpert.library.operations.db.hibernate.CategoryJPA;
import br.com.softexpert.library.operations.db.hibernate.Manager;

public class CreateCategory{
	private Operations<Category> categories= new CategoryJPA(Manager.getEntityManager());

	public void create(){
		Category c = new Category();
		String category =getDescription();
		c.setDescription(category);
		try {
			if(categories.create(c))
				JOptionPane.showMessageDialog(null, "Categoria cadastrada.");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "O campo Categoria deve ser preenchido.");
			create();
			e.printStackTrace();
		}
	}
	public String getDescription(){
		String description=(JOptionPane.showInputDialog("Digite a categoria do livro: "));
		if (description.isEmpty()) {
			JOptionPane.showMessageDialog(null, "O campo Categoria deve ser preenchido.");
			getDescription();
		}
		return description;
	}
	public void returnMessage(){
		JOptionPane.showMessageDialog(null,"Não foi possível encontrar a categoria, uma nova categoria será cadastrada.");
	}
}
