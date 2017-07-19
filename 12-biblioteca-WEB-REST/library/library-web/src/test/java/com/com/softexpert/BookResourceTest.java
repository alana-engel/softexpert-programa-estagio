package com.com.softexpert;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.junit.Test;

import br.com.softexpert.library.entity.Author;
import br.com.softexpert.library.entity.Book;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class BookResourceTest extends TestResource {

	/*@Test
    public void testGetBook() {
        Book responseMsg = target.path("book").path("81").request().get(Book.class);
       // assertEquals(responseMsg.getSequentialCode(), 70);
        assertThat(responseMsg.getSequentialCode(), is(equalTo(70)));
    }
    
    @Test
    public void testGetBookAsJSON() {
        Response responseMsg = target.path("book").path("70").request().get();
        //assertThat(responseMsg.getStatus(), equalTo(200));
        String json = responseMsg.readEntity(String.class);
        System.out.println(json);
       // assertEquals(responseMsg.getSequentialCode(), 70);
        assertThat(json, containsString("Ana"));
    }
    
    @Test
    public void testPostBook() {
    	Book b = new Book();
    	b= target.path("book").path("70").request().get(Book.class);
    	b.setSequentialCode(0);
        Book responseMsg = target.path("book").request().post(Entity.json(b), Book.class);
       // assertEquals(responseMsg.getSequentialCode(), 70);
        assertThat(responseMsg.getSequentialCode(), not(equalTo(0)));
    }

    
   @Test
    public void testDeleteBook() {
	   	Response responseMsg = target.path("book").path("84").request().delete();
        //assertEquals(responseMsg.getSequentialCode(), 70);
        String json = responseMsg.readEntity(String.class);
        System.out.println(json);
       // assertEquals(responseMsg.getSequentialCode(), 70);
        assertThat(json, containsString("true"));
    }
    
  @Test
   public void testUpdateBook() {
	    Book b= target.path("book").path("70").request().get(Book.class);
   		b.setTitle("teste Auteração");
   		Response responseMsg = target.path("book").request().put(Entity.json(b));
       //assertEquals(responseMsg.getSequentialCode(), 70);
       String json = responseMsg.readEntity(String.class);
      // System.out.println(json);
      // assertEquals(responseMsg.getSequentialCode(), 70);
       assertThat(json, containsString("true"));
   }*/
}
