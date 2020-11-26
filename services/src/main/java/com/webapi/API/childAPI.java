package com.webapi.API;

import java.util.ArrayList;
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

import com.google.gson.Gson;
import com.webapi.models.Personne;
import com.webapi.models.Response;
import com.webapi.services.ChildService;
import com.webapi.services.UserService;

@Path("/child")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.APPLICATION_JSON)
public class childAPI {

	@POST
	@Path("/ChildcourseInscription")
	
	public boolean ChildcourseInscription(
			@PathParam("childFname") String childFname,
			@PathParam("courseLevel") String courseLevel
			) {
		ChildService cs = new ChildService(childFname);
		
		return cs.ChildcourseInscription(courseLevel);
	}
	
	@GET
    @Path("/getChildCourses/{childFname}")
	public String getChildCourses(@PathParam("childFname") String childFname) {
		
		ChildService cs = new ChildService(childFname);
		String json = new Gson().toJson(cs.getChildCourses());
		
		return json;
		
	}
	
	@GET
    @Path("/getChildBdate/{childFname}")
	public String addNewChild(@PathParam("childFname") String childFname) {
		ChildService cs = new ChildService(childFname);
		return cs.getChildBdate();
	}
}
