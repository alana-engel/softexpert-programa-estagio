package br.com.softexpert.library.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Test;

import br.com.softexpert.library.entity.Author;
import br.com.softexpert.library.entity.Category;
import br.com.softexpert.library.exception.RecordException;
import br.com.softexpert.library.entity.Book;

import br.com.softexpert.library.library.DateOperations;
import br.com.softexpert.library.operations.memory.BooksInMemory;
import br.com.softexpert.library.repository.Repository;

public class BooksInMemoryTest {
	BooksInMemory booksInMemory= new BooksInMemory();
	DateOperations d = new DateOperations();
	
	@After
    public void clear() {
		Repository.getAuthors().clear();
		Repository.getBooks().clear();
		Repository.getCategories().clear();
    }
	

	 public List<Author> createAuthor() {
		Author author = new Author();
		author.setName("Alana");
		author.setNationality("b");
		List<Author> list = new ArrayList<Author>();
		list.add(author);
		return list;
    }
	
	public Category createCategory(){
		Category category = new Category();
		category.setDescription("des");
		return category;
	}
	
	@Test
	public void testCreateBook() throws RecordException{ 
		Book book = new Book();
		book.setTitle("livro");
		book.setLocation("Local");
		book.setAuthorsList(createAuthor());
		book.setCategory(createCategory());
		assertTrue(booksInMemory.create(book));
	}
	
	@Test(expected = RecordException.class)
	public void testCreatBookWithoutCategory() throws Exception{	
		Book book = new Book();
		book.setTitle("livro");
		book.setSummary("resumo");
		book.setPages(1);
		book.setLocation("Local");
		book.setAuthorsList(createAuthor());
		book.setAcquisition(d.getConvertedDate("10/06/2000"));
		assertTrue(booksInMemory.create(book));
	}
	
	@Test(expected = RecordException.class)
	public void testCreatBookWithoutAutors() throws Exception{	
		Book book = new Book();
		book.setTitle("livro");
		book.setSummary("resumo");
		book.setPages(1);
		book.setLocation("Local");
		book.setAuthorsList(null);
		book.setCategory(createCategory());
		book.setAcquisition(d.getConvertedDate("10/06/2000"));
		assertTrue(booksInMemory.create(book));
	}
	
	
	@Test(expected = RecordException.class)
	public void testCreatBookWithoutTitle() throws Exception {	
		Book book = new Book();
		book.setTitle("");
		book.setLocation("Local");
		book.setAuthorsList(createAuthor());
		book.setCategory(createCategory());
		book.setAcquisition(d.getConvertedDate("10/06/2000"));
		booksInMemory.create(book);
	}
	
	@Test(expected = RecordException.class)
	public void testCreatBookWithoutLocation() throws Exception{	
		Book book = new Book();
		book.setTitle("livro");
		book.setLocation("");
		book.setAuthorsList(createAuthor());
		book.setCategory(createCategory());
		book.setAcquisition(d.getConvertedDate("10/06/2000"));
		booksInMemory.create(book);
	}
	
	@Test
	public void testUpdateBook() throws RecordException {
		Book book = new Book();
		book.setTitle("livro");
		book.setLocation("Local");
		book.setAuthorsList(createAuthor());
		book.setCategory(createCategory());
		assertTrue(booksInMemory.create(book));
		Book book2 = new Book();
		book2.setTitle("titulo2");
		book2.setLocation("Local2");
		book2.setAuthorsList(createAuthor());
		book2.setCategory(createCategory());
		booksInMemory.update(book, book2);
		booksInMemory.searchByTitle("titulo2");
	}

	@Test (expected = RecordException.class)
	public void testUpdateBookWithoutLocation()throws RecordException{
		Book book = new Book();
		book.setTitle("livro");
		book.setLocation("Local");
		book.setAuthorsList(createAuthor());
		book.setCategory(createCategory());
		assertTrue(booksInMemory.create(book));
		Book book2 = new Book();
		book2.setTitle("titulo2");
		book2.setLocation("");
		book2.setAuthorsList(createAuthor());
		book2.setCategory(createCategory());
		booksInMemory.update(book, book2);
	}
	
	@Test (expected = RecordException.class)
	public void testUpdateBookWithoutCategory()throws RecordException{
		Book book = new Book();
		book.setTitle("livro");
		book.setLocation("Local");
		book.setAuthorsList(createAuthor());
		book.setCategory(createCategory());
		assertTrue(booksInMemory.create(book));
		Book book2 = new Book();
		book2.setTitle("titulo2");
		book2.setLocation("l");
		book2.setAuthorsList(createAuthor());
		booksInMemory.update(book, book2);
	}
	
	@Test (expected = RecordException.class)
	public void testUpdateBookWithoutAuthor()throws Exception{
		Book book = new Book();
		book.setTitle("livro");
		book.setLocation("Local");
		book.setAuthorsList(createAuthor());
		book.setCategory(createCategory());
		assertTrue(booksInMemory.create(book));
		Book book2 = new Book();
		book2.setTitle("titulo2");
		book2.setLocation("l");
		book2.setCategory(createCategory());
		book2.setAuthorsList(null);
		booksInMemory.update(book, book2);
	}
	
	@Test (expected = RecordException.class)
	public void testUpdateBookWithoutTitle()throws RecordException{
		Book book = new Book();
		book.setTitle("livro");
		book.setLocation("Local");
		book.setAuthorsList(createAuthor());
		book.setCategory(createCategory());
		assertTrue(booksInMemory.create(book));
		Book book2 = new Book();
		book2.setTitle("");
		book2.setLocation("l");
		book2.setAuthorsList(createAuthor());
		book2.setCategory(createCategory());
		booksInMemory.update(book, book2);
	} 
	
	@Test
	public void testSearchBookByCode() throws RecordException{
		
		Book book2 = new Book();
		book2.setTitle("livro8");
		book2.setLocation("Local");
		book2.setAuthorsList(createAuthor());
		book2.setCategory(createCategory());
		booksInMemory.create(book2);
		int n=book2.getSequentialCode();
		assertEquals(booksInMemory.searchByCode(n),book2);
	}
	
	@Test(expected = RecordException.class)
	public void testSearchBookByCodeNotFound() throws RecordException {
		booksInMemory.searchByCode(500000);
	}
	
	@Test
	public void testSearchBookByTitle() throws Exception{
		Book book = new Book();
		book.setTitle("livro2");
		book.setLocation("l2");
		book.setAuthorsList(createAuthor());
		book.setCategory(createCategory());
		book.setAcquisition(d.getConvertedDate("10/06/2000"));
		booksInMemory.create(book);
		List<Book> listBook = new ArrayList<Book>();
		listBook.add(book);
		assertEquals(booksInMemory.searchByTitle("livro2"),listBook);
	}
	
	@Test(expected = RecordException.class)
	public void testSearchBookByTitleNotFound() throws RecordException {
		booksInMemory.searchByTitle("test");
	}

	@Test
	public void testSearchBookByCategory() throws Exception{
		Book book = new Book();
		book.setTitle("livro3");
		book.setLocation("l");
		book.setAuthorsList(createAuthor());
		book.setCategory(createCategory());
		book.setAcquisition(d.getConvertedDate("10/06/2000"));
		booksInMemory.create(book);
		List<Book> listBook = new ArrayList<Book>();
		listBook.add(book);
		assertEquals(booksInMemory.searchByCategory("des"),listBook);
	}
	
	@Test(expected = RecordException.class)
	public void testSearchBookByCategoryNotFound() throws RecordException {
		booksInMemory.searchByCategory("test");
	}
	
	@Test
	public void testSearchBookByAuthor() throws Exception{
		Book bookA = new Book();
		bookA.setTitle("livro");
		bookA.setLocation("l");
		bookA.setAuthorsList(createAuthor());
		bookA.setCategory(createCategory());
		bookA.setAcquisition(d.getConvertedDate("10/06/2000"));
		booksInMemory.create(bookA);
		List<Book> listBook = new ArrayList<Book>();
		listBook.add(bookA);
		assertEquals(booksInMemory.searchByAuthor("Alana"),listBook);
	}
	
	
	@Test(expected = RecordException.class)
	public void testSearchBookByAuthorNotFound() throws RecordException {
		booksInMemory.searchByAuthor("test");
	}

	@Test(expected = RecordException.class)
	public void testDeleteBook() throws RecordException{
	
		Book book = new Book();
		book.setTitle("livroExcluir");
		book.setLocation("Local");
		book.setAuthorsList(createAuthor());
		book.setCategory(createCategory());
		assertTrue(booksInMemory.create(book));
		assertTrue(booksInMemory.delete("livroExcluir"));
		booksInMemory.searchByTitle("livroExcluir");
	}

	@Test
	public void testDeleteBookNotExists(){
		assertFalse(booksInMemory.delete("teste nao remove"));
	}
	
	@Test
	public void testCheckIfBookExists() throws RecordException{
		Book book = new Book();
		book.setTitle("livro5");
		book.setLocation("Local");
		book.setAuthorsList(createAuthor());
		book.setCategory(createCategory());
		assertTrue(booksInMemory.create(book));
		int n=book.getSequentialCode();
		assertFalse(booksInMemory.checkIfExists(n)==-1);
	}
	
	@Test
	public void testCheckIfBookNotExists(){
		assertTrue(booksInMemory.checkIfExists(500000)==-1);
	}
	
}
