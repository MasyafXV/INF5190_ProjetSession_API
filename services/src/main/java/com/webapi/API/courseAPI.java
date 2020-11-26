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
import com.webapi.services.CourseService;
import com.webapi.services.UserService;

@Path("/course")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.APPLICATION_JSON)
public class courseAPI {

	@GET
	@Path("/getAllCourses")
	
	public String getAllCourses() {
		
		CourseService cs = new CourseService();
		
		String json = new Gson().toJson(cs.getAllCourses());
		
		return json;
		
	}

}
