package br.com.softexpert.library.interfaces;

import java.util.List;

import br.com.softexpert.library.entity.Book;

public interface Books extends Operations<Book>{
	
	Book searchByCode(int n);
	
	List<Book> searchByTitle(String title);
	
	List<Book> searchByCategory(String category);
	
	List<Book> searchByAuthor(String n);
	
	int checkIfBookExists(int n);

}
