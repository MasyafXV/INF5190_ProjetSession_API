package com.webapi.API;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import com.google.gson.Gson;

import com.webapi.services.UserService;

import org.json.JSONObject;

//http://localhost:8080/services/webapi/user/getAllChilds/User2

@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class userAPI {
	public userAPI() {}

	@GET
	@Path("/getUserPassword/{userName}")
	public String getUserPassword(@PathParam("userName") String userName) {
		UserService us = new UserService(userName);
		return us.getUserPassword();
	}
	
	@POST
    @Path("/createUser")
	public String createUser(InputStream incomingData) {
		BufferedReader streamReader = null;
		try {
			streamReader = new BufferedReader(new InputStreamReader(incomingData, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		StringBuilder responseStrBuilder = new StringBuilder();

		String inputStr;
		try {
			while ((inputStr = streamReader.readLine()) != null)
			    responseStrBuilder.append(inputStr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject newUser =new JSONObject(responseStrBuilder.toString());
		System.out.println("printing data : " + newUser.toString());
		
		UserService us = new UserService(newUser.getJSONObject("newUser").getString("userName"));
		us.createUser(newUser.getJSONObject("newUser").getString("password"),
					  newUser.getJSONObject("newUser").getString("firstname"),
					  newUser.getJSONObject("newUser").getString("userName"),
					  newUser.getJSONObject("newUser").getString("lastname"),
					  newUser.getJSONObject("newUser").getString("email"),
					  newUser.getJSONObject("newUser").getString("adress"),
					  newUser.getJSONObject("newUser").getString("bdate"));
		return new Gson().toJson(true);
	}
	
	@POST
    @Path("/addNewChild")
	public String addNewChild(InputStream incomingData) {
		BufferedReader streamReader = null;
		try {
			streamReader = new BufferedReader(new InputStreamReader(incomingData, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		StringBuilder responseStrBuilder = new StringBuilder();

		String inputStr;
		try {
			while ((inputStr = streamReader.readLine()) != null)
			    responseStrBuilder.append(inputStr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject newchild =new JSONObject(responseStrBuilder.toString());
		System.out.println("printing data : " + newchild.toString());
		
		UserService us = new UserService(newchild.getJSONObject("child").getString("parent_userName"));
		us.addNewChild(newchild.getJSONObject("child").getString("child_firstname"), newchild.getJSONObject("child").getString("child_lastname"), newchild.getJSONObject("child").getString("child_bdate"));
		
		return new Gson().toJson(true);
	}
	
	@POST
    @Path("/courseInscription")
	public String courseInscription(InputStream incomingData) {
		BufferedReader streamReader = null;
		try {
			streamReader = new BufferedReader(new InputStreamReader(incomingData, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		StringBuilder responseStrBuilder = new StringBuilder();

		String inputStr;
		try {
			while ((inputStr = streamReader.readLine()) != null)
			    responseStrBuilder.append(inputStr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject inscription =new JSONObject(responseStrBuilder.toString());
		System.out.println("printing data : " + inscription.toString());
		
		UserService us = new UserService(inscription.getJSONObject("inscription").getString("userName"));
		us.courseInscription(inscription.getJSONObject("inscription").getString("course_code"));
		
		return new Gson().toJson(true);
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
	public String getUserCourses(@PathParam("userName") String userName) {
		
		UserService us = new UserService(userName);
		String json = new Gson().toJson(us.getUserCourses());
		
		return json;
	}
}
