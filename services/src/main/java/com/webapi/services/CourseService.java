package com.webapi.services;

import java.util.ArrayList;

import org.bson.Document;

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

}
