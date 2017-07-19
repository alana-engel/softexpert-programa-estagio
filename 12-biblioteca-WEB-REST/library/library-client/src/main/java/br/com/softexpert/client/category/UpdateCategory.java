package br.com.softexpert.client.category;
import javax.swing.JOptionPane;

import br.com.softexpert.library.entity.Category;
import br.com.softexpert.library.interfaces.Operations;
import br.com.softexpert.library.operations.db.hibernate.CategoryJPA;
import br.com.softexpert.library.operations.db.hibernate.Manager;


public class UpdateCategory{
	private Operations<Category> categories= new CategoryJPA(Manager.getEntityManager());

	public void update(){
		Category category = new Category();
		int id=Integer.parseInt(JOptionPane.showInputDialog("Digite o código da categoria: "));
		try {
			category = categories.search(id);
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Categoria não encontrada.");
			update();
			return;
		}
		String description=JOptionPane.showInputDialog("Digite a nova descrição da categoria: ");
		category.setDescription(description);
		try {
			categories.update(category);
			JOptionPane.showMessageDialog(null, "Categoria alterada.");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "O campo Categoria deve ser preenchido.");
			update();
		}
	}
}


