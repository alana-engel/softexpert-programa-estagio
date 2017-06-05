package br.com.softexpert.library.interfaces;

import java.util.List;

import br.com.softexpert.library.entity.Author;

public interface Authors extends Operations<Author>{
	
	int checkIfAuthorExists(String name);
	
	List<Author> getAuthors();

	void setAuthors(List<Author> authors);
}

