package br.com.softexpert.library.interfaces;



import br.com.softexpert.library.entity.Author;

public interface Authors extends Operations<Author>{
	
	int checkIfAuthorExists(String name);

}

