package com.webapi.services;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.Arrays;

import org.bson.Document;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import com.webapi.mangodb.DatabaseManager;

public class UserService {
	
	String userName="";
	DatabaseManager dbManager;
	MongoClient client;
	MongoDatabase mydatabase;
	
	public UserService (String userName) {
		
		this.userName=userName;	
		dbManager = new DatabaseManager();
		client = dbManager.connect();
		mydatabase = dbManager.getDatabase(client);
	}
	
	public String getUserPassword() {
		

	     MongoCollection<Document> collection = mydatabase.getCollection("UserCredentials");
	     
	    	
	    	Document UserDoc = collection.find(eq("userName", userName)).first();
	    	
	    	String password="";
	    	
	    	if (UserDoc !=null) {
		    	System.out.println(UserDoc.toJson());
		    	System.out.println(UserDoc.getString("password"));
		    	password=UserDoc.getString("password");

	    		
	    	}	
		
		return password;
		
	}
	
	public boolean createUser(
			String userName,
			String password,
			String firstname,
			String lastname,
			String email,
			String adress,
			String bdate) {
		

		
	    MongoCollection<Document> UsersCollection = mydatabase.getCollection("Users");
		Document userProfile = new Document("userName", userName)
			      .append("firstname", firstname)
			      .append("lastname", lastname)
			      .append("email", email)
			      .append("adress", adress)
			      .append("bdate", bdate);
		UsersCollection.insertOne(userProfile);
		
		//adding the user pass to credentials collection
	    MongoCollection<Document> UsersCredCollection = mydatabase.getCollection("UserCredentials");
		Document userCred = new Document("userName", userName)
			      .append("password", password);
		UsersCredCollection.insertOne(userCred);



		
		return true;
		
	}
	
	public boolean addNewChild(String childFname, String childLname, String childAge) {

	    MongoCollection<Document> usersCollection = mydatabase.getCollection("Users");
	    
	    //update the user
    	Document child = new Document("childs", Arrays.asList(childFname, childLname, childAge));
    	usersCollection.updateOne(eq("userName", userName), new Document("$push", child));
    	
    	//create child document in Childs collection
	    MongoCollection<Document> childsCollection = mydatabase.getCollection("Childs");
		Document child_profile = new Document("childFname", childFname)
			      .append("childLname", childLname)
			      .append("childAge", childAge);
		childsCollection.insertOne(child_profile);
		
    	Document course = new Document("RegisteredCourses", Arrays.asList("none"));
    	childsCollection.updateOne(eq("childFname", childFname), new Document("$push", course));

    	

		return true;
		
	}
	
	public boolean courseInscription(String CourseLevel) {
		
	    MongoCollection<Document> usersCollection = mydatabase.getCollection("Users");
    	
    	Document course = new Document("RegisteredCourses", Arrays.asList(CourseLevel));
    	usersCollection.updateOne(eq("userName", userName), new Document("$push", course));
    	
		return true;
		
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Object> getAllChilds() {
		
		
	    MongoCollection<Document> usersCollection = mydatabase.getCollection("Users");
    	Document UserDoc = usersCollection.find(eq("userName", userName)).first();
    	
    	ArrayList<Object> childs =null;
		childs = (ArrayList<Object>) UserDoc.get("childs");
//    	System.out.println(childs.toString());
    	
    	return childs;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Object>  getUserCourses() {
		
		
	    MongoCollection<Document> usersCollection = mydatabase.getCollection("Users");
    	Document UserDoc = usersCollection.find(eq("userName", userName)).first();

    	ArrayList<Object> courses =null;
    	courses = (ArrayList<Object>) UserDoc.get("RegisteredCourses");
    	
		return courses;
	}


}
