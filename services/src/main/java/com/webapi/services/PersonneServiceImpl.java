package com.webapi.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.webapi.models.Personne;
import com.webapi.models.Response;

@Path("/personne")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.APPLICATION_XML)
public class PersonneServiceImpl implements PersonneService {

	private static Map<Integer,Personne> persons = new HashMap<Integer,Personne>();
	
	@Override
	@POST
    @Path("/add")
	public Response addPerson(Personne p) {
		System.out.print("hello");
		Response response = new Response();
		if(persons.get(p.getId()) != null){
			response.setStatus(false);
			response.setMessage("Person Already Exists");
			return response;
		}
		persons.put(p.getId(), p);
		response.setStatus(true);
		response.setMessage("Person created successfully");
		return response;
	}

	@Override
	@GET
    @Path("/{id}/delete")
	public Response deletePerson(@PathParam("id") int id) {
		Response response = new Response();
		if(persons.get(id) == null){
			response.setStatus(false);
			response.setMessage("Person Doesn't Exists");
			return response;
		}
		persons.remove(id);
		response.setStatus(true);
		response.setMessage("Person deleted successfully");
		return response;
	}

	@Override
	@GET
	@Path("/{id}/get")
	public Personne getPerson(@PathParam("id") int id) {
		return persons.get(id);
	}
	
	@GET
	@Path("/{id}/getDummy")
	public Personne getDummyPerson(@PathParam("id") int id) {
		Personne p = new Personne();
		p.setAge(99);
		p.setName("Dummy");
		p.setId(id);
		return p;
	}

	@Override
	@GET
	@Path("/getAll")
	public Personne getAllPersons() {
		System.out.print("hello");

		Set<Integer> ids = persons.keySet();
		Personne[] p = new Personne[1];
//		int i=0;
//		for(Integer id : ids){
//			p[i] = persons.get(id);
//			i++;
//		}
		Personne px = new Personne();
		px.setName("xx");
		px.setAge(44);
		return px;
	}

}
