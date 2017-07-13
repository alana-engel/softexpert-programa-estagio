package com.com.softexpert;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import br.com.softexpert.library.entity.Book;
import br.com.softexpert.library.operations.db.hibernate.BookJPA;
import br.com.softexpert.library.operations.db.hibernate.Manager;

/**
 * Root resource (exposed at "bookresource" path)
 */
@Path("bookresource")
public class BookResource {
	private BookJPA book= new BookJPA(Manager.getEntityManager());
	/**
	 * Method handling HTTP GET requests. The returned object will be sent
	 * to the client as "text/plain" media type.
	 *
	 * @return String that will be returned as a text/plain response.
	 */
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Book search(@PathParam("id") String id) {
		Book b = new Book();
		try {
			b =book.searchByCode(Integer.parseInt(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	@POST
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	public Book create(Book b) {
		try {
			book.create(b);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}
	
	@PUT
	@Path("/update")
	public Boolean update(Book b) {
		try {
			book.update(b);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	@DELETE
	@Path("/{id}")
	public boolean delete(@PathParam("id") String id) {
		return book.delete(id);	
	}

	
}
