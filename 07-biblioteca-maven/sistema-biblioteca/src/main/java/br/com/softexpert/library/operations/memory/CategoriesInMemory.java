package br.com.softexpert.library.operations.memory;

import br.com.softexpert.library.entity.Category;
import br.com.softexpert.library.exception.RecordException;
import br.com.softexpert.library.interfaces.Categories;
import br.com.softexpert.library.repository.Repository;


public class CategoriesInMemory implements Categories{

	//@Override
	public boolean create(Category c){
		SequentialCode cod = new SequentialCode();
		c.setSequentialCode(cod.categoryCode());
		if (c.getDescription().isEmpty()) {
			return false;
		}else{
			Repository.getCategories().add(c);
		}
		return true;
	}
	//@Override
	public void update(Category c, int position) throws RecordException{
		if(c.getDescription().isEmpty())
			throw new RecordException("Não foi possível alterar a Categoria. Verifique os campos preenchidos.");
		Repository.getCategories().get(position).setDescription(c.getDescription());  
	}
	//@Override
	public Category search(String description) throws RecordException{
		boolean found = false;
		Category c = new Category();
		for (int i=0;i<Repository.getCategories().size();i++){
			if (Repository.getCategories().get(i).getDescription().equalsIgnoreCase(description)) {
				c = Repository.getCategories().get(i);
				found = true;
			}
		}
		if(found== false) 
			throw new RecordException("Não foi possível encontrar a categoria.");
		return c;
	}
	public int checkIfCategoryExists(String description){
		for (int i=0;i<Repository.getCategories().size();i++){
			if (Repository.getCategories().get(i).getDescription().equalsIgnoreCase(description)) {
				return i;
			}
		}
		return -1;
	}

	//@Override
	public boolean delete(String description){

		for (int i=0;i<Repository.getCategories().size();i++){
			if (Repository.getCategories().get(i).getDescription().equalsIgnoreCase(description)) {
				Repository.getCategories().remove(i);
				return true;
			}
		}
		return false;

	}

}
