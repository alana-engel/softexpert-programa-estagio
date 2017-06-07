package br.com.softexpert.library.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import br.com.softexpert.library.library.CreateRecordException;
import org.junit.Test;

import br.com.softexpert.library.entity.Author;
import br.com.softexpert.library.entity.Category;
import br.com.softexpert.library.entity.Book;

import br.com.softexpert.library.library.DateOperations;
import br.com.softexpert.library.operations.memory.BooksInMemory;

public class BooksInMemoryTest {
	BooksInMemory booksInMemory= new BooksInMemory();
	DateOperations d = new DateOperations();
	@Test
	public void testCreateBook(){ 
		Author author = new Author();
		author.setName("Alana");
		author.setNationality("b");
		List<Author> list = new ArrayList<>();
		list.add(author);
		Category category = new Category();
		category.setDescription("descricao categoria");
		Book book = new Book();
		book.setTitle("livro");
		book.setLocation("Local");
		book.setAuthorsList(list);
		book.setCategory(category);
		assertTrue(booksInMemory.create(book));
	}
	
	@Test(expected = CreateRecordException.class)
	public void testCreatBookWithoutCategory() throws RuntimeException{	
		Author author = new Author();
		author.setName("Alana");
		author.setNationality("b");
		List<Author> list = new ArrayList<>();
		list.add(author);
		Book book = new Book();
		book.setTitle("livro");
		book.setSummary("resumo");
		book.setPages(1);
		book.setLocation("Local");
		book.setAuthorsList(list);
		book.setAcquisition(d.getConvertedDate("10/06/2000"));
		assertTrue(booksInMemory.create(book));
	}
	
	@Test(expected = CreateRecordException.class)
	public void testCreatBookWithoutAutors() throws RuntimeException{	
		Category category = new Category();
		category.setDescription("descricao categoria");
		Book book = new Book();
		book.setTitle("livro");
		book.setSummary("resumo");
		book.setPages(1);
		book.setLocation("Local");
		book.setAuthorsList(null);
		book.setCategory(category);
		book.setAcquisition(d.getConvertedDate("10/06/2000"));
		assertTrue(booksInMemory.create(book));
	}
	
	
	@Test(expected = CreateRecordException.class)
	public void testCreatBookWithoutTitle() throws RuntimeException{	
		Author author = new Author();
		author.setName("Alana");
		author.setNationality("b");
		List<Author> list = new ArrayList<>();
		list.add(author);
		Category category = new Category();
		category.setDescription("descricao categoria");
		Book book = new Book();
		book.setTitle("");
		book.setLocation("Local");
		book.setAuthorsList(list);
		book.setCategory(category);
		book.setAcquisition(d.getConvertedDate("10/06/2000"));
		booksInMemory.create(book);
	}
	
	@Test(expected = CreateRecordException.class)
	public void testCreatBookWithoutLocation() throws RuntimeException{	
		Author author = new Author();
		author.setName("Alana");
		author.setNationality("b");
		List<Author> list = new ArrayList<>();
		list.add(author);
		Category category = new Category();
		category.setDescription("descricao categoria");
		Book book = new Book();
		book.setTitle("livro");
		book.setLocation("");
		book.setAuthorsList(list);
		book.setCategory(category);
		book.setAcquisition(d.getConvertedDate("10/06/2000"));
		booksInMemory.create(book);
	}
	
	@Test 
	public void testAddAuthor(){
		
	}
	
	@Test 
	public void testAddCategory(){
		
	}
	
	@Test//
	public void testUpdateBook() {//
		Author author = new Author();
		author.setName("Alana");
		author.setNationality("b");
		List<Author> list = new ArrayList<>();
		list.add(author);
		Category category = new Category();
		category.setDescription("descricao categoria");
		Book book = new Book();
		book.setTitle("livro");
		book.setLocation("Local");
		book.setAuthorsList(list);
		book.setCategory(category);
		assertTrue(booksInMemory.create(book));
		int n=book.getSequentialCode();
		Book book2 = new Book();
		book2.setTitle("titulo2");
		book2.setLocation("Local2");
		book2.setAuthorsList(list);
		book2.setCategory(category);
		assertTrue(booksInMemory.update(book2,n));
		booksInMemory.searchByTitle("titulo2");
	}

	@Test (expected = CreateRecordException.class)
	public void testNotUpdateAuthor()throws RuntimeException{
		Author author = new Author();
		author.setName("Alana");
		author.setNationality("b");
		List<Author> list = new ArrayList<>();
		list.add(author);
		Category category = new Category();
		category.setDescription("descricao categoria");
		Book book = new Book();
		book.setTitle("livro");
		book.setLocation("Local");
		book.setAuthorsList(list);
		book.setCategory(category);
		assertTrue(booksInMemory.create(book));
		int n=book.getSequentialCode();
		Book book2 = new Book();
		book2.setTitle("titulo2");
		book2.setLocation("");
		book2.setAuthorsList(list);
		book2.setCategory(category);
		assertTrue(booksInMemory.update(book2,n));
	}
	
	@Test
	public void testSearchBookByCode(){
		Author author1 = new Author();
		author1.setName("Ana");
		author1.setNationality("b");
		List<Author> listAuthors = new ArrayList<>();
		listAuthors.add(author1);
		Category category1 = new Category();
		category1.setDescription("descr");
		Book book2 = new Book();
		book2.setTitle("livro8");
		book2.setLocation("Local");
		book2.setAuthorsList(listAuthors);
		book2.setCategory(category1);
		//book2.setSequencialCode(cod.bookCode());
		booksInMemory.create(book2);
		int n=book2.getSequentialCode();
		assertEquals(booksInMemory.searchByCode(n),book2);
	}
	
	@Test(expected = CreateRecordException.class)
	public void testSearchBookByCodeNotFound() throws RuntimeException {
		booksInMemory.searchByCode(500000);
	}
	
	@Test
	public void testSearchBookByTitle(){
		Author author = new Author();
		author.setName("Alana2");
		author.setNationality("b2");
		List<Author> list = new ArrayList<>();
		list.add(author);
		Category category = new Category();
		category.setDescription("descricao categoria2");
		Book book = new Book();
		book.setTitle("livro2");
		book.setLocation("l2");
		book.setAuthorsList(list);
		book.setCategory(category);
		book.setAcquisition(d.getConvertedDate("10/06/2000"));
		booksInMemory.create(book);
		List<Book> listBook = new ArrayList<>();
		listBook.add(book);
		assertEquals(booksInMemory.searchByTitle("livro2"),listBook);
	}
	
	@Test(expected = CreateRecordException.class)
	public void testSearchBookByTitleNotFound() throws RuntimeException {
		booksInMemory.searchByTitle("test");
	}

	@Test
	public void testSearchBookByCategory(){
		Author author = new Author();
		author.setName("Alana3");
		author.setNationality("b3");
		List<Author> list = new ArrayList<>();
		list.add(author);
		Category category = new Category();
		category.setDescription("des3");
		Book book = new Book();
		book.setTitle("livro3");
		book.setLocation("l");
		book.setAuthorsList(list);
		book.setCategory(category);
		book.setAcquisition(d.getConvertedDate("10/06/2000"));
		booksInMemory.create(book);
		List<Book> listBook = new ArrayList<>();
		listBook.add(book);
		assertEquals(booksInMemory.searchByCategory("des3"),listBook);
	}
	
	@Test(expected = CreateRecordException.class)
	public void testSearchBookByCategoryNotFound() throws RuntimeException {
		booksInMemory.searchByCategory("test");
	}
	
	@Test
	public void testSearchBookByAuthor(){
		Author author = new Author();
		author.setName("A");
		author.setNationality("b");
		List<Author> list = new ArrayList<>();
		list.add(author);
		Category category = new Category();
		category.setDescription("des");
		Book bookA = new Book();
		bookA.setTitle("livro");
		bookA.setLocation("l");
		bookA.setAuthorsList(list);
		bookA.setCategory(category);
		bookA.setAcquisition(d.getConvertedDate("10/06/2000"));
		booksInMemory.create(bookA);
		List<Book> listBook = new ArrayList<>();
		listBook.add(bookA);
		assertEquals(booksInMemory.searchByAuthor("A"),listBook);
	}
	
	
	@Test(expected = CreateRecordException.class)
	public void testSearchBookByAuthorNotFound() throws RuntimeException {
		booksInMemory.searchByAuthor("test");
	}

	@Test(expected = CreateRecordException.class)
	public void testDeleteBook() throws RuntimeException{
		Author author = new Author();
		author.setName("Alana4");
		author.setNationality("b4");
		List<Author> list = new ArrayList<>();
		list.add(author);
		Category category = new Category();
		category.setDescription("descricao categori4a");
		Book book = new Book();
		book.setTitle("livroExcluir");
		book.setLocation("Local");
		book.setAuthorsList(list);
		book.setCategory(category);
		assertTrue(booksInMemory.create(book));
		assertTrue(booksInMemory.delete("livroExcluir"));
		booksInMemory.searchByTitle("livroExcluir");
	}

	@Test
	public void testDeleteBookNotExists(){
		assertFalse(booksInMemory.delete("teste nao remove"));
	}
	
	@Test
	public void testCheckIfBookExists(){
		Author author = new Author();
		author.setName("Alana5");
		author.setNationality("b5");
		List<Author> list = new ArrayList<>();
		list.add(author);
		Category category = new Category();
		category.setDescription("descricao categoria5");
		Book book = new Book();
		book.setTitle("livro5");
		book.setLocation("Local");
		book.setAuthorsList(list);
		book.setCategory(category);
		assertTrue(booksInMemory.create(book));
		int n=book.getSequentialCode();
		assertFalse(booksInMemory.checkIfBookExists(n)==-1);
	}
	
	@Test
	public void testCheckIfBookNotExists(){
		assertTrue(booksInMemory.checkIfBookExists(500000)==-1);
	}
	
}
