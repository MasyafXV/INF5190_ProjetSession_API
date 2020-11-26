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
import com.webapi.services.UserService;

//http://localhost:8080/services/webapi/user/getAllChilds/User2

@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class userAPI {

	@GET
	@Path("/getUserPassword/{userName}")
	public String getUserPassword(@PathParam("userName") String userName) {
		UserService us = new UserService(userName);
		return us.getUserPassword();
	}
	
	@POST
    @Path("/createUser/{userName}/{password}/{firstname}/{lastname}/{email}/{adress}/{bdate}")
	public boolean createUser(
			@PathParam("userName") String userName,
			@PathParam("password") String password,
			@PathParam("firstname") String firstname,
			@PathParam("lastname") String lastname,
			@PathParam("email") String email,
			@PathParam("adress") String adress,
			@PathParam("bdate") String bdate) {
		UserService us = new UserService(userName);
		us.createUser(userName, password, firstname, lastname, email, adress, bdate);
		return true;
	}
	
	@POST
    @Path("/addNewChild/{userName}/{childFname}/{childLname}/{childAge}")
	public boolean addNewChild(@PathParam("userName") String userName,
			@PathParam("childFname") String childFname,
			@PathParam("childLname") String childLname,
			@PathParam("childAge") String childAge
			) {
		UserService us = new UserService(userName);
		us.addNewChild(childFname, childLname, childAge);
		return true;
	}
	
	@POST
    @Path("/courseInscription/{userName}/{CourseLevel}")
	public boolean courseInscription(
			@PathParam("CourseLevel") String CourseLevel,
			@PathParam("userName") String userName) {
		UserService us = new UserService(userName);
		us.courseInscription(CourseLevel);
		return true;
	}
	@GET
    @Path("/getAllChilds/{userName}")
	public String getAllChilds(@PathParam("userName") String userName) {
		UserService us = new UserService(userName);
		
		String json = new Gson().toJson(us.getAllChilds());
		
		return json;
	}
	@GET
    @Path("/getUserCourses/{userName}")
	public ArrayList<Object> getUserCourses(@PathParam("userName") String userName) {
		UserService us = new UserService(userName);
		return us.getUserCourses();
	}
}
