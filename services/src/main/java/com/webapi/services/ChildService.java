package com.webapi.services;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.Arrays;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import com.webapi.mangodb.DatabaseManager;

public class ChildService {
	
	private String childFname;
	DatabaseManager dbManager;
	MongoClient client;
	MongoDatabase mydatabase;
	
	public ChildService(String childFname) {
		
		this.childFname=childFname;
		dbManager = new DatabaseManager();
		client = dbManager.connect();
		mydatabase = dbManager.getDatabase(client);
	}

	public boolean ChildcourseInscription(String courseLevel) {
		
		
	    MongoCollection<Document> childsCollection = mydatabase.getCollection("Childs");
	  
    	
    	Document course = new Document("RegisteredCourses", Arrays.asList(courseLevel));
    	childsCollection.updateOne(eq("childFname", childFname), new Document("$push", course));
    	
		return true;
		
	}

	public ArrayList<Object> getChildCourses() {
		
		
	    MongoCollection<Document> usersCollection = mydatabase.getCollection("Childs");
    	Document childDoc = usersCollection.find(eq("childFname", childFname)).first();

    	ArrayList<Object> courses =null;
    	courses = (ArrayList<Object>) childDoc.get("RegisteredCourses");
    	System.out.println(courses.toString());

		return courses;
	}
	

	public String getChildBdate() {
		
	    MongoCollection<Document> usersCollection = mydatabase.getCollection("Childs");
    	Document childDoc = usersCollection.find(eq("childFname", childFname)).first();

    	String bdate =  (String) childDoc.get("childAge");
    	
		return bdate;
	}

}
