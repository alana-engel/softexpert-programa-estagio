package com.com.softexpert;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import br.com.softexpert.library.entity.Author;
import br.com.softexpert.library.interfaces.Operations;
import br.com.softexpert.library.operations.db.hibernate.AuthorJPA;
import br.com.softexpert.library.operations.db.hibernate.Manager;

/**
 * Root resource (exposed at "authorresource" path)
 */
@Path("authorresource")
public class AuthorResource {
	private Operations<Author> authors= new AuthorJPA(Manager.getEntityManager());
	/**
	 * Method handling HTTP GET requests. The returned object will be sent
	 * to the client as "text/plain" media type.
	 *
	 * @return String that will be returned as a text/plain response.
	 */	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Author search(@PathParam("id") String id) {
		Author author = new Author();
		try {
			author =authors.search(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return author;
	}

	@POST
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	public Author create(Author author) {
		try {
			authors.create(author);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return author;
	}

	@PUT
	@Path("/update")
	public Boolean update(Author author) {
		try {
			authors.update(author);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	@DELETE
	@Path("/{id}")
	public boolean delete(@PathParam("id") String id) {
		return authors.delete(id);	
	}
}
