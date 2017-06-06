package br.com.softexpert.library.test;

import static org.junit.Assert.*;

import org.junit.Test;
import br.com.softexpert.library.entity.Category;
import br.com.softexpert.library.library.CreateRecordException;
import br.com.softexpert.library.operations.memory.CategoriesInMemory;

public class CategoriesInMemoryTest {
	CategoriesInMemory categoriesInMemory = new CategoriesInMemory();

	@Test
	public void testCreateCategory(){
		Category c = new Category();
		c.setDescription("Descric�o");
		assertTrue(categoriesInMemory.create(c));
	}

	@Test
	public void testNotCreateCategory(){	
		Category c = new Category();
		c.setDescription("");
		assertFalse(categoriesInMemory.create(c));
	}

	@Test
	public void testSearchCategory(){
		Category c2 = new Category();	
		c2.setDescription("d");
		categoriesInMemory.create(c2);
		assertEquals(categoriesInMemory.search("d"),c2);
	}
	
	@Test(expected = CreateRecordException.class)
	public void testCategoryNotFound() throws RuntimeException {
		categoriesInMemory.search("test");
	}

	@Test
	public void testUpdateCategory(){
		Category c = new Category();
		c.setDescription("Descric�o");
		categoriesInMemory.create(c);
		Category c1 = new Category();
		c1.setDescription("Descri��o alterada");
		assertTrue(categoriesInMemory.update(c1,0));
	}

	@Test
	public void testNotUpdateCategory(){
		Category c = new Category();
		c.setDescription("Descric�o");
		categoriesInMemory.create(c);
		Category c1 = new Category();
		c1.setDescription("");
		assertFalse(categoriesInMemory.update(c1,0));
	}

	@Test
	public void testDeleteCategory(){
		Category c = new Category();
		c.setDescription("Descric�o");
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
		assertFalse(categoriesInMemory.checkIfCategoryExists("d")==-1);
	}
	@Test
	public void testCheckIfCategoryNotExists(){
		assertTrue(categoriesInMemory.checkIfCategoryExists("teste nao existe")==-1);
	}

}
