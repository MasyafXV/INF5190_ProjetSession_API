package com.webapi.API;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.webapi.services.SessionService;


@Path("/session")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class sessionAPI {
	
	@GET
	@Path("/getSessions")
	
	public String getSessions() throws ParseException {
		SessionService sessionService = new SessionService();
		
		String json = new Gson().toJson(sessionService.getAllSessions());
		
		return json;
	}
	
	@POST
	@Path("/createNewSession")
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
		JSONObject newSession =new JSONObject(responseStrBuilder.toString());
		System.out.println("printing data : " + newSession.toString());
		
		SessionService sessionService = new SessionService();
		sessionService.createSession(newSession);
		
		return new Gson().toJson(true);
	}
}
