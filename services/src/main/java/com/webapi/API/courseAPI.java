package com.webapi.API;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.webapi.services.CourseService;

@Path("/course")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class courseAPI {

	@GET
	@Path("/getAllCourses")

	public String getAllCourses() {

		CourseService cs = new CourseService();

		String json = new Gson().toJson(cs.getAllCourses());

		return json;

	}

	@POST
	@Path("/createNewCourse")
	public String createNewSession(InputStream incomingData) {
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
		JSONObject newCourse = new JSONObject(responseStrBuilder.toString());
		System.out.println("printing data : " + newCourse.toString());

		CourseService cservice = new CourseService();
		cservice.createCourse(newCourse);

		return new Gson().toJson(true);
	}

	@POST
	@Path("/setNewStudent")
	public String setNewStudent(InputStream incomingData) {
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
		JSONObject newStudent = new JSONObject(responseStrBuilder.toString());
		System.out.println("printing data : " + newStudent.toString());

		CourseService cService = new CourseService();
		cService.setNewStudent(newStudent);

		return new Gson().toJson(true);
	}

}
