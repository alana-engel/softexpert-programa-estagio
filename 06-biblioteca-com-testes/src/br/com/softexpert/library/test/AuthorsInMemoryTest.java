package br.com.softexpert.library.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.com.softexpert.library.entity.Author;
import br.com.softexpert.library.exception.RecordException;
import br.com.softexpert.library.operations.memory.AuthorsInMemory;

public class AuthorsInMemoryTest {
	AuthorsInMemory authorsInMemory = new AuthorsInMemory();
	
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
        thrown.expectMessage("N�o foi poss�vel cadastrar o autor. Preencha o campo Nome.");
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
	
	@Test
	public void testAuthorNotFound() throws RecordException {
		thrown.expect(RecordException.class);
        thrown.expectMessage("N�o foi poss�vel encontrar o autor.");
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

	@Test
	public void testNotUpdateAuthor()throws Exception{
		thrown.expect(RecordException.class);
        thrown.expectMessage("N�o foi poss�vel alterar o autor. Preencha o campo Nome.");
		Author author = new Author();
		author.setName("Alana");
		authorsInMemory.create(author);
		Author author2 = new Author();
		author2.setName("");
		authorsInMemory.update(author2,0);
	}

	@Test
	public void testDeleteAuthor() throws Exception{
		thrown.expect(RecordException.class);
        thrown.expectMessage("N�o foi poss�vel encontrar o autor.");
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
