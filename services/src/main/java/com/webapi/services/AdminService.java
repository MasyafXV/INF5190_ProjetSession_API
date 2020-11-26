package com.webapi.services;
import com.webapi.mangodb.DatabaseManager;

import static com.mongodb.client.model.Filters.eq;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


public class AdminService {
	
	String userName="";
	DatabaseManager dbManager;
	MongoClient client;
	MongoDatabase mydatabase;

	public AdminService(String userName) {

		this.userName = userName;
		dbManager = new DatabaseManager();
		client = dbManager.connect();
		mydatabase = dbManager.getDatabase(client);
	}

	public String getUserPassword() {

		MongoCollection<Document> collection = mydatabase.getCollection("AdminCredentials");
	    
		Document UserDoc = collection.find(eq("userName", userName)).first();
		
	    	String password="";
	    	
	    	if (UserDoc !=null) {
		    	System.out.println(UserDoc.toJson());
		    	System.out.println(UserDoc.getString("password"));
		    	password=UserDoc.getString("password");

	    		
	    	}	

		
		return password;
		
	}
}
