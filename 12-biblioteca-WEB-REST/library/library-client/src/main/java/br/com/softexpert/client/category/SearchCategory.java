package br.com.softexpert.client.category;
import javax.swing.JOptionPane;
import br.com.softexpert.library.entity.Category;
import br.com.softexpert.library.interfaces.Operations;
import br.com.softexpert.library.operations.memory.CategoriesInMemory;


public class SearchCategory{
	private Operations<Category> categories= new CategoriesInMemory();

	public void search(){
		int id=Integer.parseInt(JOptionPane.showInputDialog("Digite o código da categoria: "));
		try {
			JOptionPane.showMessageDialog(null, categories.search(id));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Não foi possível encontrar a categoria.");
		}

	}
}
