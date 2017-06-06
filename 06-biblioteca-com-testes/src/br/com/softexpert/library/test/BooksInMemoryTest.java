package br.com.softexpert.library.test;

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
	public void testCreateAuthor(){
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
		booksInMemory.create(book);
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
		booksInMemory.create(book);
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
	
	/*@Test(expected = CreateRecordException.class)
	public void testNotCreateAuthor() throws RuntimeException{	
		Author author = new Author();
		author.setName("");
		booksInMemory.create(author);
	}

	@Test
	public void testSearchAuthor(){
		Author author = new Author();
		author.setName("Alana");
		author.setNationality("b");
		booksInMemory.create(author);
		assertEquals(booksInMemory.search("Alana"),author);
	}
	
	@Test(expected = CreateRecordException.class)
	public void testAuthorNotFound() throws RuntimeException {
		booksInMemory.search("test");
	}

	@Test(expected = CreateRecordException.class)
	public void testUpdateAuthor() throws RuntimeException{
		Author author = new Author();
		author.setName("Alana");
		booksInMemory.create(author);
		Author author2 = new Author();
		author2.setName("Ana");
		assertTrue(booksInMemory.update(author2,0));
		booksInMemory.search("Alana");
	}

	@Test
	public void testNotUpdateAuthor(){
		Author author = new Author();
		author.setName("Alana");
		booksInMemory.create(author);
		Author author2 = new Author();
		author2.setName("");
		assertTrue(booksInMemory.update(author2,0));
		booksInMemory.search("Alana");
	}

	@Test(expected = CreateRecordException.class)
	public void testDeleteAuthor() throws RuntimeException{
		Author author = new Author();
		author.setName("Alana");
		booksInMemory.create(author);
		assertTrue(booksInMemory.delete("Alana"));
		booksInMemory.search("Alana");
	}

	@Test
	public void testDeleteAuthorNotExists(){
		assertFalse(booksInMemory.delete("teste nao remove"));
	}
	
	@Test
	public void testCheckIfAuthorExists(){
		Author author = new Author();
		author.setName("Alana");
		author.setNationality("b");
		booksInMemory.create(author);
		assertFalse(booksInMemory.checkIfAuthorExists("Alana")==-1);
	}
	
	@Test
	public void testCheckIfCategoryNotExists(){
		assertTrue(booksInMemory.checkIfAuthorExists("teste nao existe")==-1);
	}*/
	
}
