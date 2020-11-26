package com.uqam.inf5190.labo10.service;

import com.uqam.inf5190.labo10.model.Personne;
import com.uqam.inf5190.labo10.model.Response;

public interface PersonneService {

	public Response addPerson(Personne p);
	
	public Response deletePerson(int id);
	
	public Personne getPerson(int id);
	
	public Personne[] getAllPersons();

}
