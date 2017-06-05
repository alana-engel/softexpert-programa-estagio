package br.com.softexpert.library.operations.memory;
import java.util.ArrayList;
import java.util.List;

import br.com.softexpert.library.entity.Category;
import br.com.softexpert.library.interfaces.Categories;
public class CategoriesInMemory implements Categories{

	private static List<Category> categories = new ArrayList<Category>();
	private Category category = new Category();
	private SequentialCode cod = new SequentialCode();

	@Override
	public boolean create(Category c){
		category=c;
		category.setSequentialCode(cod.categoryCode());
		if (category.getDescription().isEmpty()) {
			return false;
		}else{
			categories.add(category);
		}
		return true;
	}
	@Override
	public boolean update(Category c, int position){
		categories.get(position).setDescription(c.getDescription());  
		return true;
	}
	@Override
	public Category search(String description){
		Category c = new Category();
		for (int i=0;i<categories.size();i++){
			if (categories.get(i).getDescription().equalsIgnoreCase(description)) {
				c = categories.get(i);
			}
		}
		return c;
	}
	public int checkIfCategoryExists(String description){
		for (int i=0;i<categories.size();i++){
			if (categories.get(i).getDescription().equalsIgnoreCase(description)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public boolean delete(String description){

		for (int i=0;i<categories.size();i++){
			if (categories.get(i).getDescription().equalsIgnoreCase(description)) {
				categories.remove(i);
				return true;
			}
		}
		return false;

	}

	public List<Category> getCategories() {
		return categories;
	}
	public void setCategories(List<Category> categories) {
		CategoriesInMemory.categories = categories;
	}



}
