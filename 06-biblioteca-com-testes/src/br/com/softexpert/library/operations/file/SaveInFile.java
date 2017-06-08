package br.com.softexpert.library.operations.file;

public class SaveInFile {	
	AuthorsInFile authors = new AuthorsInFile();
	CategoriesInFile categories = new CategoriesInFile();
	BooksInFile books = new BooksInFile(); 
	Code c=new Code();
	public void saveRecordsInFile(){
		books.saveBooksInFile();
		authors.saveAuthorsInFile();
		categories.saveCategoryInFile();
		c.saveInFileCodeCategory();
		c.saveInFileCodeBook();
		c.saveInFileCodeAuthor();	
	}	
	public void getRecordsFromFile(){
		books.getBooks();
		authors.getAuthors();
		categories.getCategories();
		c.getCategoryCode();
		c.getAuthorCode();
		c.getBookCode();
	}
}
