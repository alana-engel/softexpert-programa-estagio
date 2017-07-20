package com.com.softexpert;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.junit.Test;

import com.fasterxml.classmate.GenericType;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import br.com.softexpert.library.entity.Author;
import br.com.softexpert.library.entity.Book;
import br.com.softexpert.library.entity.Category;
import br.com.softexpert.library.library.DateOperations;

public class BookResourceTest extends TestResource {
	
	DateOperations date = new DateOperations();
	private Author getAuthor(){
		Author author = new Author();
		author.setName("Maria");
		author.setBirthday(date.getConvertedDate("29/12/1994"));
		author.setNationality("b");
		author.setSequentialCode(0);
		return author;
	}
	private Category getCategory(){
		Category c = new Category();
		c.setSequentialCode(0);
		c.setDescription("des");
		return c;
	}
	private Book getBook(){
		Book book = new Book();
		book.setAcquisition(date.getConvertedDate("15/12/2000"));
		List<Author> list = new ArrayList();
		list.add(getAuthor());
		book.setAuthorsList(list);
		book.setCategory(getCategory());
		book.setLocation("local");
		book.setPages(123);
		book.setSequentialCode(0);
		book.setSummary("resumo");
		book.setTitle("titulo");
		return book;
	}
	
	@Test
    public void testGetBook() {
		Book responseMsgCreate = target.path("book").request().post(Entity.json(getBook()), Book.class);
		Book responseMsg = target.path("book").path("1").request().get(Book.class);
        assertThat(responseMsg.getSequentialCode(), is(equalTo(1)));
    }
	/*
    @Test
    public void testGetBookAsJSON() {
        Response responseMsg = target.path("book").path("71").request().get();
        String json = responseMsg.readEntity(String.class);
        System.out.println(json);
        assertThat(json, containsString("livro"));
    }
 
    @Test
	public void testBookNotFound(){
		Response responseMsg = target.path("author").path("300").request().get();
		assertThat(responseMsg.getStatus(), equalTo(500));
	}
  
    @Test
    public void testPostBook() {
    	Book b = target.path("book").path("71").request().get(Book.class);
    	b.setSequentialCode(0);
        Book responseMsg = target.path("book").request().post(Entity.json(b), Book.class);
        assertThat(responseMsg.getSequentialCode(), not(equalTo(0)));
    }
	@Test
	public void testNotCreateBook(){	
		Book b = target.path("book").path("71").request().get(Book.class);
    	b.setSequentialCode(0);
    	b.setTitle("");
        Response responseMsg = target.path("book").request().post(Entity.json(b));
		assertThat(responseMsg.getStatus(), equalTo(500));
	} */
   /*
   @Test
    public void testDeleteBook() {
	   	Response responseMsg = target.path("book").path("120").request().delete();
        String json = responseMsg.readEntity(String.class);
        assertThat(json, containsString("true"));
    }
  
   @Test
	public void testDeleteAuthorNotExists(){
	  	Response responseMsg = target.path("book").path("1000").request().delete();
        String json = responseMsg.readEntity(String.class);
		assertThat(json, containsString("false"));
	}
  
  @Test
   public void testUpdateBook() {
	    Book b= target.path("book").path("71").request().get(Book.class);
   		b.setTitle("teste Alteração");
   		Response responseMsg = target.path("book").request().put(Entity.json(b));
        String json = responseMsg.readEntity(String.class);
        assertThat(json, containsString("true"));
   }
   @Test
   public void testNotUpdateBook() {
	    Book b= target.path("book").path("71").request().get(Book.class);
   		b.setTitle("teste Alteração");
   		Response responseMsg = target.path("book").request().put(Entity.json(b));
        String json = responseMsg.readEntity(String.class);
        assertThat(json, containsString("true"));
   }
    */
}
