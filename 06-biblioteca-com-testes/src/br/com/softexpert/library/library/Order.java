package br.com.softexpert.library.library;

import java.util.Collections;

import br.com.softexpert.library.repository.Repository;

public class Order {
	public void ordena(){
		Collections.sort(Repository.getBooks());	
	}
}