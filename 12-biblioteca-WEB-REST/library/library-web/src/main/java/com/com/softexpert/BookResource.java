package com.com.softexpert;

import javax.ws.rs.Consumes;
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
 * Root resource (exposed at "book" path)
 */
@Path("book")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {
	private BookJPA books= new BookJPA(Manager.getEntityManager());
	/**
	 * Method handling HTTP GET requests. The returned object will be sent
	 * to the client as "text/plain" media type.
	 *
	 * @return String that will be returned as a text/plain response.
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Book search(@PathParam("id") String id) throws NumberFormatException, Exception {
		return books.searchByCode(Integer.parseInt(id));
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Book create(Book b) throws Exception {
		books.create(b);
		return b;
	}

	@PUT
	public EntityResponse update(Book book) throws Exception {
		boolean updated;
		try {
			books.update(book);
			updated = true;
		} catch (Exception e) {
			updated = false;
		}
		return new EntityResponse(updated, "Livro alterado com sucesso");
	}

	@DELETE
	@Path("/{id}")
	public EntityResponse delete(@PathParam("id") String id) {
		boolean deleted = books.delete(Integer.parseInt(id));
		return new EntityResponse(deleted, "Livro excluído com sucesso");	
	}


}
