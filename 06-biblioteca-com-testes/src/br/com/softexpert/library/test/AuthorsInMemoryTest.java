package br.com.softexpert.library.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


import org.junit.Test;

import br.com.softexpert.library.entity.Author;
import br.com.softexpert.library.library.CreateRecordException;
import br.com.softexpert.library.operations.memory.AuthorsInMemory;

public class AuthorsInMemoryTest {
	AuthorsInMemory authorsInMemory = new AuthorsInMemory();

	@Test
	public void testCreateAuthor(){
		Author author = new Author();
		author.setName("Alana");
		author.setNationality("b");
		assertTrue(authorsInMemory.create(author));
	}

	@Test(expected = CreateRecordException.class)
	public void testNotCreateAuthor() throws RuntimeException{	
		Author author = new Author();
		author.setName("");
		authorsInMemory.create(author);
	}

	@Test
	public void testSearchAuthor(){
		Author author = new Author();
		author.setName("Alana");
		author.setNationality("b");
		authorsInMemory.create(author);
		assertEquals(authorsInMemory.search("Alana"),author);
	}
	
	@Test(expected = CreateRecordException.class)
	public void testAuthorNotFound() throws RuntimeException {
		authorsInMemory.search("test");
	}

	@Test(expected = CreateRecordException.class)
	public void testUpdateAuthor() throws RuntimeException{
		Author author = new Author();
		author.setName("Alana");
		authorsInMemory.create(author);
		Author author2 = new Author();
		author2.setName("Ana");
		assertTrue(authorsInMemory.update(author2,0));
		authorsInMemory.search("Alana");
	}

	@Test
	public void testNotUpdateAuthor(){
		Author author = new Author();
		author.setName("Alana");
		authorsInMemory.create(author);
		Author author2 = new Author();
		author2.setName("");
		assertTrue(authorsInMemory.update(author2,0));
		authorsInMemory.search("Alana");
	}

	@Test(expected = CreateRecordException.class)
	public void testDeleteAuthor() throws RuntimeException{
		Author author = new Author();
		author.setName("Alana");
		authorsInMemory.create(author);
		assertTrue(authorsInMemory.delete("Alana"));
		authorsInMemory.search("Alana");
	}

	@Test
	public void testDeleteAuthorNotExists(){
		assertFalse(authorsInMemory.delete("teste nao remove"));
	}
	
	@Test
	public void testCheckIfAuthorExists(){
		Author author = new Author();
		author.setName("Alana");
		author.setNationality("b");
		authorsInMemory.create(author);
		assertFalse(authorsInMemory.checkIfAuthorExists("Alana")==-1);
	}
	
	@Test
	public void testCheckIfCategoryNotExists(){
		assertTrue(authorsInMemory.checkIfAuthorExists("teste nao existe")==-1);
	}
}
