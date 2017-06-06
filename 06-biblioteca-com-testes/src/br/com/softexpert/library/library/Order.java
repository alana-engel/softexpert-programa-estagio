package br.com.softexpert.library.library;

import java.util.Collections;

import br.com.softexpert.library.repository.Repository;

public class Order {
	Repository repository = new Repository();
	public void ordena(){
		Collections.sort(repository.getBooks());
	}
}