package br.com.softexpert.library.operations.memory;

import br.com.softexpert.library.entity.Category;
import br.com.softexpert.library.interfaces.Categories;
import br.com.softexpert.library.library.CreateRecordException;
import br.com.softexpert.library.repository.Repository;


public class CategoriesInMemory implements Categories{
	
	Repository repository = new Repository();
	
	@Override
	public boolean create(Category c){
		SequentialCode cod = new SequentialCode();
		c.setSequentialCode(cod.categoryCode());
		if (c.getDescription().isEmpty()) {
			return false;
		}else{
			repository.getCategories().add(c);
		}
		return true;
	}
	@Override
	public boolean update(Category c, int position){
		if(c.getDescription().isEmpty())
			return false;
		repository.getCategories().get(position).setDescription(c.getDescription());  
		return true;
	}
	@Override
	public Category search(String description){
		boolean found = false;
		Category c = new Category();
		for (int i=0;i<repository.getCategories().size();i++){
			if (repository.getCategories().get(i).getDescription().equalsIgnoreCase(description)) {
				c = repository.getCategories().get(i);
				found = true;
			}
		}
		if(found== false) 
			throw new CreateRecordException("Não foi possível encontrar a categoria.");
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
