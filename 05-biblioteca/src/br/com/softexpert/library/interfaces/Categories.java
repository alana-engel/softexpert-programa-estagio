package br.com.softexpert.library.interfaces;

import java.util.List;

import br.com.softexpert.library.entity.Category;

public interface Categories extends Operations<Category>{
	
	int checkIfCategoryExists(String description);
	
	List<Category> getCategories();

	void setCategories(List<Category> categories);
}
