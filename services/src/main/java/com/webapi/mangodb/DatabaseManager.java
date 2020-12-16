package com.webapi.mangodb;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.Arrays;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class DatabaseManager {
	MongoClient mongoClient;

	public DatabaseManager() {
		init();

	}

	public void init() {
		System.out.println("Database initialisation...");

		// here we create the collections and documents to populate our database, ex:
		// users, courses etc..
		mongoClient = MongoClients.create("mongodb://localhost:27017");

		// check if Courses collection already exists
		if (collectionExists("Courses")) {
			// do nothing if exists

		} else {
			MongoDatabase mydatabase = mongoClient.getDatabase("MyDatabase");

			// creating courses
			MongoCollection<Document> CoursesCollection = mydatabase.getCollection("Courses");
			Document Etoile_de_mer = new Document("courseLevel", "Etoile_de_mer").append("sessionCode", "A20")
					.append("firstname", "").append("description", "").append("NbPlace", "30").append("price", "79.99")
					.append("prerequisite", Arrays.asList("none"));

			Document Bambins = new Document("courseLevel", "Bambins").append("sessionCode", "A20")
					.append("firstname", "").append("description", "").append("NbPlace", "30").append("price", "79.99")
					.append("prerequisite", Arrays.asList("Etoile_de_mer"));

			Document Tortues = new Document("courseLevel", "Tortues").append("sessionCode", "A20")
					.append("firstname", "").append("description", "").append("NbPlace", "30").append("price", "79.99")
					.append("prerequisite", Arrays.asList("Etoile_de_mer", "Bambins"));

			Document Pingouins = new Document("courseLevel", "Pingouins").append("sessionCode", "A20")
					.append("firstname", "").append("description", "").append("NbPlace", "30").append("price", "79.99")
					.append("prerequisite", Arrays.asList("Etoile_de_mer", "Bambins", "Tortues"));

			Document Salamandre = new Document("courseLevel", "Salamandre").append("sessionCode", "A20")
					.append("firstname", "").append("description", "").append("NbPlace", "30").append("price", "79.99")
					.append("prerequisite", Arrays.asList("Etoile_de_mer", "Bambins", "Tortues", "Pingouins"));

			Document Baleines = new Document("courseLevel", "Baleines").append("sessionCode", "A20")
					.append("firstname", "").append("description", "").append("NbPlace", "30").append("price", "79.99")
					.append("prerequisite",
							Arrays.asList("Etoile_de_mer", "Bambins", "Tortues", "Pingouins", "Salamandre"));

			Document Grenouilles = new Document("courseLevel", "Grenouilles").append("sessionCode", "A20")
					.append("firstname", "").append("description", "").append("NbPlace", "30").append("price", "79.99")
					.append("prerequisite", Arrays.asList("Etoile_de_mer", "Bambins", "Tortues", "Pingouins",
							"Salamandre", "Baleines"));

			Document Dauphins = new Document("courseLevel", "Dauphins").append("sessionCode", "A20")
					.append("firstname", "").append("description", "").append("NbPlace", "30").append("price", "79.99")
					.append("prerequisite", Arrays.asList("Etoile_de_mer", "Bambins", "Tortues", "Pingouins",
							"Salamandre", "Baleines", "Grenouilles"));

			Document Junior1 = new Document("courseLevel", "Junior1").append("sessionCode", "A20")
					.append("firstname", "").append("description", "").append("NbPlace", "30").append("price", "79.99")
					.append("prerequisite", Arrays.asList("Etoile_de_mer", "Bambins", "Tortues", "Pingouins",
							"Salamandre", "Baleines", "Grenouilles", "Dauphins"));

			Document Junior2 = new Document("courseLevel", "Junior2").append("sessionCode", "A20")
					.append("firstname", "").append("description", "").append("NbPlace", "30").append("price", "79.99")
					.append("prerequisite", Arrays.asList("Etoile_de_mer", "Bambins", "Tortues", "Pingouins",
							"Salamandre", "Baleines", "Grenouilles", "Dauphins", "Junior1"));

			Document Junior3 = new Document("courseLevel", "Junior3").append("sessionCode", "A20")
					.append("firstname", "").append("description", "").append("NbPlace", "30").append("price", "79.99")
					.append("prerequisite", Arrays.asList("Etoile_de_mer", "Bambins", "Tortues", "Pingouins",
							"Salamandre", "Baleines", "Grenouilles", "Dauphins", "Junior1", "Junior2"));

			Document Junior4 = new Document("courseLevel", "Junior4").append("sessionCode", "A20")
					.append("firstname", "").append("description", "").append("NbPlace", "30").append("price", "79.99")
					.append("prerequisite", Arrays.asList("Etoile_de_mer", "Bambins", "Tortues", "Pingouins",
							"Salamandre", "Baleines", "Grenouilles", "Dauphins", "Junior1", "Junior2", "Junior3"));

			Document Junior5 = new Document("courseLevel", "Junior5").append("sessionCode", "A20")
					.append("firstname", "").append("description", "").append("NbPlace", "30").append("price", "79.99")
					.append("prerequisite",
							Arrays.asList("Etoile_de_mer", "Bambins", "Tortues", "Pingouins", "Salamandre", "Baleines",
									"Grenouilles", "Dauphins", "Junior1", "Junior2", "Junior3", "Junior4"));

			Document Maitre_Nageur = new Document("courseLevel", "Maitre_Nageur").append("sessionCode", "A20")
					.append("firstname", "").append("description", "").append("NbPlace", "30").append("price", "79.99")
					.append("prerequisite", Arrays.asList("Age higher than 16"));

			CoursesCollection.insertOne(Etoile_de_mer);
			CoursesCollection.insertOne(Bambins);
			CoursesCollection.insertOne(Tortues);
			CoursesCollection.insertOne(Pingouins);
			CoursesCollection.insertOne(Salamandre);
			CoursesCollection.insertOne(Baleines);
			CoursesCollection.insertOne(Grenouilles);
			CoursesCollection.insertOne(Dauphins);
			CoursesCollection.insertOne(Junior1);
			CoursesCollection.insertOne(Junior2);
			CoursesCollection.insertOne(Junior3);
			CoursesCollection.insertOne(Junior4);
			CoursesCollection.insertOne(Junior5);
			CoursesCollection.insertOne(Maitre_Nageur);

		}

		// check if Sessions collection already exists
		if (collectionExists("Sessions")) {
			// do nothing if exists

		} else {
			MongoDatabase mydatabase = mongoClient.getDatabase("MyDatabase");

			// creating courses
			MongoCollection<Document> SessionsCollection = mydatabase.getCollection("Sessions");
			Document Automne20 = new Document();
			Automne20 = new Document("sessionCode", "A20").append("season", "Automne").append("year", "2020")
					.append("sessionFrom", "08/09/2020").append("sessionTo", "22/12/2020");

			SessionsCollection.insertOne(Automne20);

		}

		// check if UserCredentials collection already exists
		if (collectionExists("UserCredentials")) {
			// if exists do nothing
		} else {
			System.out.println("Database is creating user stuff...");

			// creating users credentials
			MongoDatabase mydatabase = mongoClient.getDatabase("MyDatabase");
			Document user1Cred = new Document("userName", "User1").append("password", "pass1").append("arrayTest",
					Arrays.asList("v3.2", "v3.0", "v2.6"));
			Document user2Cred = new Document("userName", "User2").append("password", "pass2").append("arrayTest",
					Arrays.asList("v3.2", "v3.0", "v2.6"));
			MongoCollection<Document> UCredcollection = mydatabase.getCollection("UserCredentials");
			UCredcollection.insertOne(user1Cred);
			UCredcollection.insertOne(user2Cred);

			// creating user profiles
			MongoCollection<Document> UsersCollection = mydatabase.getCollection("Users");
			Document user1_Profile = new Document("userName", "User1").append("firstname", "John")
					.append("lastname", "Doe").append("email", "John@example.com").append("adress", "999 av.xxx")
					.append("bdate", "1979-09-15");

			Document user2_Profile = new Document("userName", "User2").append("firstname", "Smith")
					.append("lastname", "Jael").append("email", "Smith@example.com").append("adress", "9191 av.yyy")
					.append("bdate", "1998-05-28");
			UsersCollection.insertOne(user1_Profile);
			UsersCollection.insertOne(user2_Profile);

			Document course = new Document("RegisteredCourses", Arrays.asList("none"));
			UsersCollection.updateOne(eq("userName", "User1"), new Document("$push", course));
			UsersCollection.updateOne(eq("userName", "User2"), new Document("$push", course));

		}

		// check if AdminCredentials collection already exists
		if (collectionExists("AdminCredentials")) {
			// if exists do nothing
		} else {
			System.out.println("Database is creating admin stuff...");

			MongoDatabase mydatabase = mongoClient.getDatabase("MyDatabase");

			Document admin1 = new Document("userName", "admintest1").append("password", "pass1").append("arrayTest",
					Arrays.asList("v3.2", "v3.0", "v2.6"));
			Document admin2 = new Document("userName", "admintest2").append("password", "pass2").append("arrayTest",
					Arrays.asList("v3.2", "v3.0", "v2.6"));
			MongoCollection<Document> collection = mydatabase.getCollection("AdminCredentials");
			collection.insertOne(admin1);
			collection.insertOne(admin2);

		}
	}

	public MongoClient connect() {

		MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
		return mongoClient;

	}

	public MongoDatabase getDatabase(MongoClient mongoClient) {

		MongoDatabase mydatabase = mongoClient.getDatabase("MyDatabase");

		return mydatabase;
	}

	private boolean collectionExists(String collection) {

		return mongoClient.getDatabase("MyDatabase").listCollectionNames().into(new ArrayList<String>())
				.contains(collection);

	}

}
