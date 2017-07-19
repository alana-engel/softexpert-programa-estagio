package br.com.softexpert.library.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.com.softexpert.library.entity.Author;
import br.com.softexpert.library.exception.RecordException;
import br.com.softexpert.library.operations.memory.AuthorsInMemory;
import br.com.softexpert.library.repository.Repository;

public class AuthorsInMemoryTest {
	AuthorsInMemory authorsInMemory = new AuthorsInMemory();

	@After
    public void clear() {
		Repository.getAuthors().clear();
		Repository.getBooks().clear();
		Repository.getCategories().clear();
    } 
	
	@Rule
    public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void testCreateAuthor() throws Exception{
		Author author = new Author();
		author.setName("Alana");
		author.setNationality("b");
		assertTrue(authorsInMemory.create(author));
	}

	@Test
	public void testNotCreateAuthor() throws Exception{	
		thrown.expect(RecordException.class);
        thrown.expectMessage("Não foi possível cadastrar o autor. Preencha o campo Nome.");
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
		assertEquals(authorsInMemory.searchByName("Alana"),author);
	}
	
	@Test
	public void testAuthorNotFound() throws RecordException {
		thrown.expect(RecordException.class);
        thrown.expectMessage("Não foi possível encontrar o autor.");
		authorsInMemory.search(-20);
	}

	@Test(expected = RecordException.class)
	public void testUpdateAuthor() throws Exception{
		Author author = new Author();
		author.setName("Alana");
		authorsInMemory.create(author);
		
		author.setName("Ana");
		authorsInMemory.update(author);
		authorsInMemory.searchByName("Alana");
	}

	@Test
	public void testNotUpdateAuthor()throws Exception{
		thrown.expect(RecordException.class);
        thrown.expectMessage("Não foi possível alterar o autor. Preencha o campo Nome.");
		Author author = new Author();
		author.setName("Alana");
		authorsInMemory.create(author);
	
		author.setName("");
		
		authorsInMemory.update(author);
	}

	@Test
	public void testDeleteAuthor() throws Exception{
		thrown.expect(RecordException.class);
        thrown.expectMessage("Não foi possível encontrar o autor.");
		Author author = new Author();
		Author author2 = new Author();
		author.setName("Alana");
		authorsInMemory.create(author);
		author2=authorsInMemory.searchByName("Alana");
		assertTrue(authorsInMemory.delete(author2.getSequentialCode()));
		authorsInMemory.search(1);
	}

	@Test
	public void testDeleteAuthorNotExists(){
		assertFalse(authorsInMemory.delete(-20));
	}
}
