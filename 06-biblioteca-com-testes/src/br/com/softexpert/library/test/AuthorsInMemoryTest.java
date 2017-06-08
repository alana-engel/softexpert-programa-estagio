package br.com.softexpert.library.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


import org.junit.Test;

import br.com.softexpert.library.entity.Author;
import br.com.softexpert.library.exception.RecordException;
import br.com.softexpert.library.operations.memory.AuthorsInMemory;

public class AuthorsInMemoryTest {
	AuthorsInMemory authorsInMemory = new AuthorsInMemory();

	@Test
	public void testCreateAuthor() throws Exception{
		Author author = new Author();
		author.setName("Alana");
		author.setNationality("b");
		assertTrue(authorsInMemory.create(author));
	}

	@Test(expected = RecordException.class)
	public void testNotCreateAuthor() throws Exception{	
		Author author = new Author();
		author.setName("");
		authorsInMemory.create(author);
	}

	@Test
	public void testSearchAuthor() throws Exception{
		Author author = new Author();
		author.setName("Alana");
		author.setNationality("b");
		authorsInMemory.create(author);
		assertEquals(authorsInMemory.search("Alana"),author);
	}
	
	@Test(expected = RecordException.class)
	public void testAuthorNotFound() throws RecordException {
		authorsInMemory.search("test");
	}

	@Test(expected = RecordException.class)
	public void testUpdateAuthor() throws Exception{
		Author author = new Author();
		author.setName("Alana");
		authorsInMemory.create(author);
		Author author2 = new Author();
		author2.setName("Ana");
		authorsInMemory.update(author2,0);
		authorsInMemory.search("Alana");
	}

	@Test(expected = RecordException.class)
	public void testNotUpdateAuthor()throws Exception{
		Author author = new Author();
		author.setName("Alana");
		authorsInMemory.create(author);
		Author author2 = new Author();
		author2.setName("");
		authorsInMemory.update(author2,0);
	}

	@Test(expected = RecordException.class)
	public void testDeleteAuthor() throws Exception{
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
	public void testCheckIfAuthorExists() throws Exception{
		Author author = new Author();
		author.setName("Alana");
		author.setNationality("b");
		authorsInMemory.create(author);
		assertFalse(authorsInMemory.checkIfAuthorExists("Alana")==-1);
	}
	
	@Test
	public void testCheckIfAuthorNotExists(){
		assertTrue(authorsInMemory.checkIfAuthorExists("teste nao existe")==-1);
	}
}
