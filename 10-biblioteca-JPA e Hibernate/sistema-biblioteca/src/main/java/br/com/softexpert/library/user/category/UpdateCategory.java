package br.com.softexpert.library.user.category;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import br.com.softexpert.library.entity.Category;
import br.com.softexpert.library.interfaces.Operations;
import br.com.softexpert.library.operations.db.hibernate.CategoryJPA;


public class UpdateCategory{
	EntityManagerFactory factory = Persistence.
			createEntityManagerFactory("library");
	EntityManager manager = factory.createEntityManager();
	private Operations<Category> categories= new CategoryJPA(manager);

	public void update(){
		Category category = new Category();
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
		category.setDescription(description);
		try {
			categories.update(category);
			JOptionPane.showMessageDialog(null, "Categoria alterada.");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "O campo Categoria deve ser preenchido.");
			update();
			e.printStackTrace();
		}
	}
}


