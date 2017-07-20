package com.com.softexpert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.com.softexpert.library.entity.Author;
import br.com.softexpert.library.exception.RecordException;
import br.com.softexpert.library.library.DateOperations;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

public class AuthorResourceTest extends TestResource{
	
	private Author getAuthor(){
		DateOperations date = new DateOperations();
		Author author = new Author();
		author.setName("Maria");
		author.setBirthday(date.getConvertedDate("29/12/1994"));
		author.setNationality("b");
		author.setSequentialCode(0);
		return author;
	}
	/*
	@Test
	public void testGetAuthor() {
		Author responseMsgCreate = target.path("author").request().post(Entity.json(getAuthor()), Author.class);
		Author responseMsg = target.path("author").path("1").request().get(Author.class);
		assertThat(responseMsg.getSequentialCode(), is(equalTo(1)));
	}

	@Test
	public void testGetAuthorAsJSON() {
		Author responseMsgCreate = target.path("author").request().post(Entity.json(getAuthor()), Author.class);
		Response responseMsg = target.path("author").path("1").request().get();
		assertThat(responseMsg.getStatus(), equalTo(200));
		String json = responseMsg.readEntity(String.class);
		assertThat(json, containsString("Maria"));
	}

	@Test
	public void testAuthorNotFound(){
		Response responseMsg = target.path("author").path("100").request().get();
		assertThat(responseMsg.getStatus(), equalTo(500));
	}

	@Test
	public void testPostAuthor() {
		Author responseMsg = target.path("author").request().post(Entity.json(getAuthor()), Author.class);
		assertThat(responseMsg.getSequentialCode(), not(equalTo(0)));
	}

	@Test
	public void testNotCreateAuthor(){	
		Author author = getAuthor();
		author.setSequentialCode(0);
		author.setName("");
		Response responseMsg = target.path("author").request().post(Entity.json(author));	   
		assertThat(responseMsg.getStatus(), equalTo(500));
	} 

	@Test
	public void testDeleteAuthor() {
		Author responseMsgCreate = target.path("author").request().post(Entity.json(getAuthor()), Author.class);
		Response responseMsg = target.path("author").path("1").request().delete();
		String json = responseMsg.readEntity(String.class);
		assertThat(json, containsString("true"));
	}

	@Test
	public void testDeleteAuthorNotExists(){
		Response responseMsg = target.path("author").path("1").request().delete();
		String json = responseMsg.readEntity(String.class);
		assertThat(json, containsString("false"));
	}


	@Test
	public void testUpdateAuthor(){
		Author a = getAuthor();
		a.setName("teste A");
		Response responseMsg = target.path("author").request().put(Entity.json(a));
		String json = responseMsg.readEntity(String.class);
		assertThat(json, containsString("true"));
	}

	@Test
	public void testNotUpdateAuthor(){
		Author a = getAuthor();
		a.setName("");
		Response responseMsg = target.path("author").request().put(Entity.json(a));
		String json = responseMsg.readEntity(String.class);
		assertThat(json, containsString("false"));
	} 
	*/
}
