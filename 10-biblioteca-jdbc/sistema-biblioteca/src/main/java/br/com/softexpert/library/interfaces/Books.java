package br.com.softexpert.library.interfaces;

import java.util.List;

import br.com.softexpert.library.entity.Book;
import br.com.softexpert.library.exception.RecordException;

public interface Books extends Operations<Book>{
	
	Book searchByCode(int n) throws RecordException;
	
	List<Book> searchByTitle(String title) throws Exception;
	
	List<Book> searchByCategory(String category) throws Exception;
	
	List<Book> searchByAuthor(String n) throws Exception;

}
