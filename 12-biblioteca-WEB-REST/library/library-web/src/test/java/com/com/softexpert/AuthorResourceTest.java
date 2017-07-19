package com.com.softexpert;
import org.junit.Test;
import br.com.softexpert.library.entity.Author;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

public class AuthorResourceTest extends TestResource{
   /* 
    @Test
    public void testGetAuthor() {
        Author responseMsg = target.path("author").path("70").request().get(Author.class);
       // assertEquals(responseMsg.getSequentialCode(), 70);
        assertThat(responseMsg.getSequentialCode(), is(equalTo(70)));
    }
    
    @Test
    public void testGetAuthorAsJSON() {
        Response responseMsg = target.path("author").path("70").request().get();
        //assertThat(responseMsg.getStatus(), equalTo(200));
        String json = responseMsg.readEntity(String.class);
        System.out.println(json);
       // assertEquals(responseMsg.getSequentialCode(), 70);
        assertThat(json, containsString("Ana"));
    }
    
    @Test
    public void testPostAuthor() {
    	Author a = new Author();
    	a= target.path("author").path("70").request().get(Author.class);
    	a.setSequentialCode(0);
        Author responseMsg = target.path("author").request().post(Entity.json(a), Author.class);
       // assertEquals(responseMsg.getSequentialCode(), 70);
        assertThat(responseMsg.getSequentialCode(), not(equalTo(0)));
    }

    
   @Test
    public void testDeleteAuthor() {
	   	Response responseMsg = target.path("author").path("84").request().delete();
        //assertEquals(responseMsg.getSequentialCode(), 70);
        String json = responseMsg.readEntity(String.class);
        System.out.println(json);
       // assertEquals(responseMsg.getSequentialCode(), 70);
        assertThat(json, containsString("true"));
    }
    
  @Test
   public void testUpdateAuthor() {
	    Author a= target.path("author").path("70").request().get(Author.class);
   		a.setName("teste Auteração");
   		Response responseMsg = target.path("author").request().put(Entity.json(a));
       //assertEquals(responseMsg.getSequentialCode(), 70);
       String json = responseMsg.readEntity(String.class);
      // System.out.println(json);
      // assertEquals(responseMsg.getSequentialCode(), 70);
       assertThat(json, containsString("true"));
   }
   */
}
