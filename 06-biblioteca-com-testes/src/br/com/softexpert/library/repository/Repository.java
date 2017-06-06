package br.com.softexpert.library.repository;

import java.util.ArrayList;
import java.util.List;

import br.com.softexpert.library.entity.Author;
import br.com.softexpert.library.entity.Book;
import br.com.softexpert.library.entity.Category;

public class Repository {
	private static List<Author> authors = new ArrayList<Author>();
	private static List<Book> books = new ArrayList<Book>();
	private static List<Category> categories = new ArrayList<Category>();
	
	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		Repository.authors = authors;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		Repository.books = books;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		Repository.categories = categories;
	}
}
