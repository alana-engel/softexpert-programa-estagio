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
		assertEquals(categoriesInMemory.searchByDescription("d"),c2);
	}
	
	@Test
	public void testCategoryNotFound() throws RecordException {
		thrown.expect(RecordException.class);
        thrown.expectMessage("Não foi possível encontrar a categoria.");
		categoriesInMemory.search(-20);
	}

	@Test
	public void testUpdateCategory() throws RecordException{
		Category c = new Category();
		c.setDescription("Descricão");
		categoriesInMemory.create(c);

		c.setDescription("Descrição alterada");
		categoriesInMemory.update(c);
	}

	@Test
	public void testNotUpdateCategory() throws RecordException{
		thrown.expect(RecordException.class);
        thrown.expectMessage("Não foi possível alterar a Categoria. Verifique os campos preenchidos.");
		Category c = new Category();
		c.setDescription("Descricão");
		categoriesInMemory.create(c);
		
		c.setDescription("");
		categoriesInMemory.update(c);
	}

	@Test
	public void testDeleteCategory(){
		Category c = new Category();
		c.setDescription("Descricão");
		categoriesInMemory.create(c);
		assertTrue(categoriesInMemory.delete(c.getSequentialCode()));
	}

	@Test
	public void testDeleteCategoryNotExists(){
		assertFalse(categoriesInMemory.delete(-20));
	}
}
