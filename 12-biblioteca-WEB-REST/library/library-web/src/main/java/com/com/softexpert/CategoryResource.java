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
import br.com.softexpert.library.entity.Category;
import br.com.softexpert.library.interfaces.Operations;
import br.com.softexpert.library.operations.db.hibernate.CategoryJPA;
import br.com.softexpert.library.operations.db.hibernate.Manager;

/**
 * Root resource (exposed at "category" path)
 */
@Path("category")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CategoryResource {
	private Operations<Category> categories= new CategoryJPA(Manager.getEntityManager());
	
	/**
	 * Method handling HTTP GET requests. The returned object will be sent
	 * to the client as "text/plain" media type.
	 *
	 * @return String that will be returned as a text/plain response.
	 * @throws Exception 
	 */
	
	@GET
	@Path("/{id}")
	public Category search(@PathParam("id") String id) throws Exception {
		return categories.search(Integer.parseInt(id));
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Category create(Category c) throws Exception {
		categories.create(c);
		return c;
	}

	@PUT
	public EntityResponse update(Category c) throws Exception {
		boolean updated;
		try {
			categories.update(c);
			updated = true;
		} catch (Exception e) {
			updated = false;
		}
	return new EntityResponse(updated, "Categoria alterada com sucesso");
	}

	@DELETE
	@Path("/{id}")
	public EntityResponse delete(@PathParam("id") String id) {
		boolean deleted = categories.delete(Integer.parseInt(id));
		return new EntityResponse(deleted, "Categoria excluída com sucesso");
	}
}
