package br.com.softexpert.library.test;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.com.softexpert.library.entity.Category;
import br.com.softexpert.library.exception.RecordException;
import br.com.softexpert.library.operations.memory.CategoriesInMemory;

public class CategoriesInMemoryTest {
	CategoriesInMemory categoriesInMemory = new CategoriesInMemory();
	
	@Rule
    public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void testCreateCategory(){
		Category c = new Category();
		c.setDescription("Descricão");
		assertTrue(categoriesInMemory.create(c));
	}

	@Test
	public void testNotCreateCategory(){	
		Category c = new Category();
		c.setDescription("");
		assertFalse(categoriesInMemory.create(c));
	}

	@Test
	public void testSearchCategory() throws RecordException{
		Category c2 = new Category();	
		c2.setDescription("d");
		categoriesInMemory.create(c2);
		assertEquals(categoriesInMemory.search("d"),c2);
	}
	
	@Test
	public void testCategoryNotFound() throws RecordException {
		thrown.expect(RecordException.class);
        thrown.expectMessage("Não foi possível encontrar a categoria.");
		categoriesInMemory.search("test");
	}

	@Test
	public void testUpdateCategory() throws RecordException{
		Category c = new Category();
		c.setDescription("Descricão");
		categoriesInMemory.create(c);
		Category c1 = new Category();
		c1.setDescription("Descrição alterada");
		categoriesInMemory.update(c, c1);
	}

	@Test
	public void testNotUpdateCategory() throws RecordException{
		thrown.expect(RecordException.class);
        thrown.expectMessage("Não foi possível alterar a Categoria. Verifique os campos preenchidos.");
		Category c = new Category();
		c.setDescription("Descricão");
		categoriesInMemory.create(c);
		Category c1 = new Category();
		c1.setDescription("");
		categoriesInMemory.update(c, c1);
	}

	@Test
	public void testDeleteCategory(){
		Category c = new Category();
		c.setDescription("Descricão");
		categoriesInMemory.create(c);
		assertTrue(categoriesInMemory.delete(c.getDescription()));
	}

	@Test
	public void testDeleteCategoryNotExists(){
		assertFalse(categoriesInMemory.delete("teste nao remove"));
	}
	
	@Test
	public void testCheckIfCategoryExists(){
		Category c2 = new Category();	
		c2.setDescription("d");
		categoriesInMemory.create(c2);
		assertFalse(categoriesInMemory.checkIfExists("d")==-1);
	}
	@Test
	public void testCheckIfCategoryNotExists(){
		assertTrue(categoriesInMemory.checkIfExists("teste nao existe")==-1);
	}

}
