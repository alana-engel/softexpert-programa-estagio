package com.com.softexpert;

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
 * Root resource (exposed at "categoryresource" path)
 */
@Path("categoryresource")
public class CategoryResource {
	private Operations<Category> categories= new CategoryJPA(Manager.getEntityManager());
	/**
	 * Method handling HTTP GET requests. The returned object will be sent
	 * to the client as "text/plain" media type.
	 *
	 * @return String that will be returned as a text/plain response.
	 */
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Category search(@PathParam("id") String id) {
		Category c = new Category();
		try {
			c =categories.search(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}

	@POST
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	public Category create(Category c) {
		try {
			categories.create(c);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}

	@PUT
	@Path("/update")
	public Boolean update(Category c) {
		try {
			categories.update(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	@DELETE
	@Path("/{id}")
	public boolean delete(@PathParam("id") String id) {
		return categories.delete(id);	
	}


}
