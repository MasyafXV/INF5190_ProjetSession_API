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

import com.webapi.models.Personne;
import com.webapi.models.Response;
import com.webapi.services.AdminService;
import com.webapi.services.UserService;

@Path("/admin")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class adminAPI {

	@GET
	@Path("/getAdminPassword/{userName}")
	public String getAdminPassword(@PathParam("userName") String userName) {
		AdminService as = new AdminService(userName);
		return as.getAdminPassword();
	}

}
