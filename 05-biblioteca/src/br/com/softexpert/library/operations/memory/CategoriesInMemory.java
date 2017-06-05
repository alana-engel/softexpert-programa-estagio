package br.com.softexpert.library.operations.memory;

import br.com.softexpert.library.entity.Category;
import br.com.softexpert.library.interfaces.Categories;
import br.com.softexpert.library.repository.Repository;
public class CategoriesInMemory implements Categories{

	
	private Category category = new Category();
	private SequentialCode cod = new SequentialCode();
	Repository repository = new Repository();
	@Override
	public boolean create(Category c){
		category=c;
		category.setSequentialCode(cod.categoryCode());
		if (category.getDescription().isEmpty()) {
			return false;
		}else{
			repository.getCategories().add(category);
		}
		return true;
	}
	@Override
	public boolean update(Category c, int position){
		repository.getCategories().get(position).setDescription(c.getDescription());  
		return true;
	}
	@Override
	public Category search(String description){
		Category c = new Category();
		for (int i=0;i<repository.getCategories().size();i++){
			if (repository.getCategories().get(i).getDescription().equalsIgnoreCase(description)) {
				c = repository.getCategories().get(i);
			}
		}
		return c;
	}
	public int checkIfCategoryExists(String description){
		for (int i=0;i<repository.getCategories().size();i++){
			if (repository.getCategories().get(i).getDescription().equalsIgnoreCase(description)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public boolean delete(String description){

		for (int i=0;i<repository.getCategories().size();i++){
			if (repository.getCategories().get(i).getDescription().equalsIgnoreCase(description)) {
				repository.getCategories().remove(i);
				return true;
			}
		}
		return false;

	}

}
