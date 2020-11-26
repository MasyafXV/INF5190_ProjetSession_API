package com.webapi.API;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
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

import org.json.JSONObject;

import com.google.gson.Gson;
import com.webapi.models.Personne;
import com.webapi.models.Response;
import com.webapi.services.ChildService;
import com.webapi.services.UserService;

@Path("/child")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class childAPI {

	@POST
	@Path("/ChildcourseInscription")
	
	public String ChildcourseInscription(InputStream incomingData){
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
		
		ChildService us = new ChildService(inscription.getJSONObject("inscription").getString("childFname"));
		us.ChildcourseInscription(inscription.getJSONObject("inscription").getString("course_code"));
		
		
		return new Gson().toJson(true);
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
