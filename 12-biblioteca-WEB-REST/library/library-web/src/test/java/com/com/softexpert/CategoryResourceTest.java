package com.com.softexpert;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import org.junit.Test;
import br.com.softexpert.library.entity.Category;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import javax.ws.rs.core.Response;
public class CategoryResourceTest extends TestResource{
/*
	 @Test
	    public void testGetCategory() {
	        Category responseMsg = target.path("category").path("70").request().get(Category.class);
	       // assertEquals(responseMsg.getSequentialCode(), 70);
	        assertThat(responseMsg.getSequentialCode(), is(equalTo(70)));
	    }
	    
	    @Test
	    public void testGetCategoryAsJSON() {
	        Response responseMsg = target.path("category").path("70").request().get();
	        //assertThat(responseMsg.getStatus(), equalTo(200));
	        String json = responseMsg.readEntity(String.class);
	        System.out.println(json);
	       // assertEquals(responseMsg.getSequentialCode(), 70);
	        assertThat(json, containsString("Ana"));
	    }
	    
	    @Test
	    public void testPostCategory() {
	    	Category c = new Category();
	    	c= target.path("category").path("70").request().get(Category.class);
	    	c.setSequentialCode(0);
	        Category responseMsg = target.path("category").request().post(Entity.json(c), Category.class);
	       // assertEquals(responseMsg.getSequentialCode(), 70);
	        assertThat(responseMsg.getSequentialCode(), not(equalTo(0)));
	    }

	    
	   @Test
	    public void testDeleteCategory() {
		   	Response responseMsg = target.path("category").path("84").request().delete();
	        //assertEquals(responseMsg.getSequentialCode(), 70);
	        String json = responseMsg.readEntity(String.class);
	        System.out.println(json);
	       // assertEquals(responseMsg.getSequentialCode(), 70);
	        assertThat(json, containsString("true"));
	    }
	    
	  @Test
	   public void testUpdateCategory() {
		    Category c= target.path("category").path("70").request().get(Category.class);
	   		c.setDescription("teste Auteração");
	   		Response responseMsg = target.path("category").request().put(Entity.json(c));
	       //assertEquals(responseMsg.getSequentialCode(), 70);
	       String json = responseMsg.readEntity(String.class);
	      // System.out.println(json);
	      // assertEquals(responseMsg.getSequentialCode(), 70);
	       assertThat(json, containsString("true"));
	   }*/
}
