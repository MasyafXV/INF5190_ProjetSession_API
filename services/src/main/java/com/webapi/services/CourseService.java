package com.webapi.services;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.Arrays;

import org.bson.Document;
import org.json.JSONObject;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import com.webapi.mangodb.DatabaseManager;
import com.webapi.models.Course;
import com.webapi.models.CoursePrerequisite;

public class CourseService {

	DatabaseManager dbManager;
	MongoClient client;
	MongoDatabase mydatabase;

	public CourseService() {

		dbManager = new DatabaseManager();
		client = dbManager.connect();
		mydatabase = dbManager.getDatabase(client);

	}

	public ArrayList<Course> getAllCourses() {

		MongoCollection<Document> courseCollection = mydatabase.getCollection("Courses");

		FindIterable<Document> iterable = courseCollection.find(); // (1)

		MongoCursor<Document> cursor = iterable.iterator(); // (2)

		ArrayList<Course> coursesList = new ArrayList<Course>();

		try {

			while (cursor.hasNext()) {

				Document course = cursor.next();

				@SuppressWarnings("unchecked")
				Object[] prerequisites = ((ArrayList<String>) course.get("prerequisite")).toArray();

				Course courseModel = new Course(course.getString("sessionCode"), course.getString("courseLevel"),
						course.getString("description"), course.getString("NbPlace"), course.getString("price"),
						prerequisites);
				coursesList.add(courseModel);
			}

		} finally {

			cursor.close();

		}

		return coursesList;

	}

	public boolean createCourse(Course newCourse) {

		MongoCollection<Document> courseCollection = mydatabase.getCollection("Courses");

		Document course = new Document("courseLevel", newCourse.getCourseLevel())
				.append("sessionCode", newCourse.getSessionCode()).append("description", newCourse.getDescription())
				.append("NbPlace", newCourse.getNbPlace()).append("price", newCourse.getprice())
				.append("prerequisite", new CoursePrerequisite().getPrerequisiteOf(newCourse.getCourseLevel()));

		courseCollection.insertOne(course);

		return true;

	}

	public boolean setNewStudent(JSONObject newStudent) {
		
	    MongoCollection<Document> coursesCollection = mydatabase.getCollection("Courses");


	    //update the course
    	Document course = new Document("students", Arrays.asList(newStudent.getJSONObject("Student").getString("userName")));
    	coursesCollection.updateOne(eq("courseLevel", newStudent.getJSONObject("Student").getString("courseLevel")), new Document("$push", course));
    	

    	//update nb place
	    MongoCollection<Document> collection = mydatabase.getCollection("Courses");
	    Document courseDoc = collection.find(eq("courseLevel", newStudent.getJSONObject("Student").getString("courseLevel"))).first();
		int  nbPlace = Integer.parseInt(courseDoc.get("NbPlace").toString()) - 1;
		
    	Document nbPlaceDoc = new Document("NbPlace",nbPlace);
    	
		System.out.println(nbPlaceDoc);
		courseDoc.put("NbPlace",String.valueOf(nbPlace));

    	coursesCollection.replaceOne(eq("courseLevel", newStudent.getJSONObject("Student").getString("courseLevel")), courseDoc);

    	
		return true;		
	}

}
