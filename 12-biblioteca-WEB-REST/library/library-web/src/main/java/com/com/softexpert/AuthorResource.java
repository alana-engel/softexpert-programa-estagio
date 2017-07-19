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
import br.com.softexpert.library.entity.Author;
import br.com.softexpert.library.interfaces.Operations;
import br.com.softexpert.library.operations.db.hibernate.AuthorJPA;
import br.com.softexpert.library.operations.db.hibernate.Manager;

/**
 * Root resource (exposed at "author" path)
 */
@Path("author")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthorResource {
	private Operations<Author> authors= new AuthorJPA(Manager.getEntityManager());
	/**
	 * Method handling HTTP GET requests. The returned object will be sent
	 * to the client as "text/plain" media type.
	 *
	 * @return String that will be returned as a text/plain response.
	 * @throws Exception 
	 */	
	@GET
	@Path("/{id}")
	public Author search(@PathParam("id") String id) throws Exception {
			return authors.search(Integer.parseInt(id));
	}

	@POST
	public Author create(Author author) throws Exception {
			authors.create(author);
		return author;
	}

	@PUT
	public EntityResponse update(Author author){
		boolean updated;
			try {
				authors.update(author);
				updated = true;
			} catch (Exception e) {
				updated = false;
			}
		return new EntityResponse(updated, "Autor alterado com sucesso");
	}
	@DELETE
	@Path("/{id}")
	public EntityResponse delete(@PathParam("id") String id) {
		boolean deleted = authors.delete(Integer.parseInt(id));
		return new EntityResponse(deleted, "Autor excluído com sucesso");
	}
}
