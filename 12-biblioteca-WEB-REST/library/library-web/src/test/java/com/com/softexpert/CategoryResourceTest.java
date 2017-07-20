package com.com.softexpert;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import org.junit.Test;
import br.com.softexpert.library.entity.Category;
import br.com.softexpert.library.exception.RecordException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertFalse;

import javax.ws.rs.core.Response;
public class CategoryResourceTest extends TestResource{
    
	private Category getCategory(){
		Category c = new Category();
		c.setSequentialCode(0);
		c.setDescription("des");
		return c;
	}
	/*
	@Test
	public void testGetCategory() {	
		Category responseMsgCreate = target.path("category").request().post(Entity.json(getCategory()), Category.class);
		Category responseMsg = target.path("category").path("1").request().get(Category.class);
		assertThat(responseMsg.getSequentialCode(), is(equalTo(1)));
	}

	@Test
	public void testGetCategoryAsJSON() {
		Category responseMsgCreate = target.path("category").request().post(Entity.json(getCategory()), Category.class);
		Response responseMsg = target.path("category").path("1").request().get();
		String json = responseMsg.readEntity(String.class);
		System.out.println(json);
		assertThat(json, containsString("des"));
	}

	@Test
	public void testPostCategory() {
		Category responseMsg = target.path("category").request().post(Entity.json(getCategory()), Category.class);
		assertThat(responseMsg.getSequentialCode(), not(equalTo(0)));
	}

	@Test
	public void testDeleteCategory() {
		Category responseMsgCreate = target.path("category").request().post(Entity.json(getCategory()), Category.class);
		Response responseMsg = target.path("category").path("1").request().delete();
		String json = responseMsg.readEntity(String.class);
		assertThat(json, containsString("true"));
	}


	@Test
	public void testUpdateCategory() {
		Category responseMsgCreate = target.path("category").request().post(Entity.json(getCategory()), Category.class);
		Category c= target.path("category").path("1").request().get(Category.class);
		c.setDescription("teste Alteração");
		Response responseMsg = target.path("category").request().put(Entity.json(c));
		String json = responseMsg.readEntity(String.class);
		assertThat(json, containsString("true"));
	}
	
	@Test
	public void testNotCreateCategory(){	
		Category c = new Category();
		c.setDescription("");
		c.setSequentialCode(0);
		Response responseMsg = target.path("category").request().post(Entity.json(c));
		assertThat(responseMsg.getStatus(), equalTo(500));
	}
	
	@Test
	public void testCategoryNotFound() {
		Response responseMsg = target.path("category").path("100").request().get();
		assertThat(responseMsg.getStatus(), equalTo(500));
	}
	
	@Test
	public void testNotUpdateCategory(){
		Category c = getCategory();
		c.setDescription("");
		Response responseMsg = target.path("category").request().put(Entity.json(c));
		String json = responseMsg.readEntity(String.class);
		assertThat(json, containsString("false"));
	}
	@Test
	public void testDeleteCategoryNotExists(){
		Response responseMsg = target.path("category").path("1").request().delete();
		String json = responseMsg.readEntity(String.class);
		assertThat(json, containsString("false"));
	}
	*/
}
