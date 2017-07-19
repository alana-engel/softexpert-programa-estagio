package br.com.softexpert.library.operations.memory;

import java.util.ArrayList;
import java.util.List;

import br.com.softexpert.library.entity.Author;
import br.com.softexpert.library.entity.Book;
import br.com.softexpert.library.exception.RecordException;
import br.com.softexpert.library.interfaces.Books;
import br.com.softexpert.library.library.Barcode;
import br.com.softexpert.library.repository.Repository;

public class BooksInMemory implements Books {
	
	@Override
	public boolean create(Book book) throws RecordException {
		SequentialCode cod = new SequentialCode();
		book.setSequentialCode(cod.bookCode());
		int code =book.getSequentialCode();
		barcode(book, code);
		if (book.getTitle().isEmpty() || book.getLocation().isEmpty()|| book.getAuthorsList()==null || book.getCategory().getDescription()==null){
			throw new RecordException("Não foi possível cadastrar o Livro. Verifique os campos preenchidos.");
		}else{
			Repository.getBooks().add(book);
		}
		return true;
	}
	
	private void barcode(Book book, int code){
		Barcode bcode=new Barcode();
		book.setBarcode(bcode.getBarcode(code));
	}
	
	public boolean delete(int id){
		for (int i=0;i<Repository.getBooks().size();i++){
			if (Repository.getBooks().get(i).getSequentialCode()== id) {
				Repository.getBooks().remove(i);
				return true;
			}
		}
		return false;
	}

	@Override
	public void update(Book book) throws RecordException{
		if (book.getTitle().isEmpty() || book.getLocation().isEmpty()|| book.getAuthorsList()==null || book.getCategory()==null){
			throw new RecordException("Não foi possível alterar o Livro. Verifique os campos preenchidos.");
		}else{
			book.setTitle(book.getTitle());
			book.setSummary(book.getSummary());
			book.setPages(book.getPages());
			book.setLocation(book.getLocation());
			book.setAcquisition(book.getAcquisition());
			book.setAuthorsList(book.getAuthorsList());
			book.setCategory(book.getCategory());
		}
	}
	
	@Override
	public Book search(int id) {
		return null;
	}

	public Book searchByCode(int code) throws RecordException{
		boolean found = false;
		Book book = new Book();
		for (int i=0;i<Repository.getBooks().size();i++){
			if (Repository.getBooks().get(i).getSequentialCode()==code) {
				book = Repository.getBooks().get(i);
				found = true;
			}
		}
		if(found== false) 
			throw new RecordException("Não foi possível encontrar o livro.");
		return book;
	}
	public List<Book> searchByTitle(String title) throws RecordException{
		boolean found = false;
		List<Book> listByTitle = new ArrayList<Book>();
		for (int i=0;i<Repository.getBooks().size();i++){
			if (Repository.getBooks().get(i).getTitle().contains(title)) {
				listByTitle.add(Repository.getBooks().get(i));
				found = true;
			}
		}
		if(found== false) 
			throw new RecordException("Não foi possível encontrar o livro.");
		return listByTitle;
	} 
	
	public List<Book> searchByCategory(String category) throws RecordException{
		boolean found = false;
		List<Book> listByCategory = new ArrayList<Book>();
		for (int i=0;i<Repository.getBooks().size();i++){
			if (Repository.getBooks().get(i).getCategory().getDescription().equalsIgnoreCase(category)) {
				listByCategory.add(Repository.getBooks().get(i));
				found = true;
			}
		}
		if(found== false) 
			throw new RecordException("Não foi possível encontrar o livro.");
		return listByCategory;
	}
	public List<Book> searchByAuthor(String name) throws RecordException{
		boolean found = false;
		List<Book> listByAuthor = new ArrayList<Book>();
		List<Author> list = new ArrayList<Author>();
		for (int i=0;i<Repository.getBooks().size();i++){
			list=Repository.getBooks().get(i).getAuthorsList();
			Author a;
			for (int j=0;j<list.size();j++){
				a=list.get(j);
				if (a.getName().equalsIgnoreCase(name)) {
					listByAuthor.add(Repository.getBooks().get(i));
					found = true;
				}
			}
		}
		if(found== false) 
			throw new RecordException("Não foi possível encontrar o livro.");
		return listByAuthor;
	}
}