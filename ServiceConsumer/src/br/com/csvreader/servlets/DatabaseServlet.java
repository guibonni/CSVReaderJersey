package br.com.csvreader.servlets;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/database")
public class DatabaseServlet {

	@GET
	@Path("/")
	@Produces("application/json")
	public Response getAll() {
		return Response.status(200).entity("Método GET não implementado").build();
	}
	
	@POST
	@Path("/")
	@Consumes("application/json")
	@Produces("application/json")
	public Response saveData() {
		//return Response.status(201).entity("O ").build();
		return Response.status(201).entity("Método POST não implementado").build();
	}

}
