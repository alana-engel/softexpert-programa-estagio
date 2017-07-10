package br.com.softexpert.library.user.category;
import javax.swing.JOptionPane;

import br.com.softexpert.library.entity.Category;

import br.com.softexpert.library.interfaces.Operations;
import br.com.softexpert.library.operations.db.CategoryDao;


public class SearchCategory{
	private Operations<Category> categories= new CategoryDao();

	public void search(){
		String description=(JOptionPane.showInputDialog("Digite a categoria: "));
		try {
			JOptionPane.showMessageDialog(null, categories.search(description));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Não foi possível encontrar a categoria.");
			e.printStackTrace();
		}

	}
}
