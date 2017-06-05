package br.com.softexpert.library.library;

import java.util.Collections;

import br.com.softexpert.library.operations.memory.BooksInMemory;

public class Order {
	private BooksInMemory bookOperations= new BooksInMemory();
	public void ordena(){
		Collections.sort(bookOperations.getBooks());
	}
}