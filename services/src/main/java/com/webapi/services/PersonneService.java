package com.webapi.services;

import com.webapi.models.Personne;
import com.webapi.models.Response;

public interface PersonneService {

	public Response addPerson(Personne p);
	
	public Response deletePerson(int id);
	
	public Personne getPerson(int id);
	
	public Personne getAllPersons();

}
