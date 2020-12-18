package com.webapi.services;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
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

	public boolean createCourse(JSONObject newCourse) {

		MongoCollection<Document> courseCollection = mydatabase.getCollection("Courses");

		Document course = new Document("courseLevel", newCourse.getString("courseLevel"))
				.append("sessionCode", newCourse.getString("sessionCode"))
				.append("description", newCourse.getString("description"))
				.append("NbPlace", newCourse.getString("NbPlace")).append("price", newCourse.getString("price"))
				.append("prerequisite", new CoursePrerequisite().getPrerequisiteOf(newCourse.getString("courseLevel")));

		courseCollection.insertOne(course);

		return true;

	}

	public boolean setNewStudent(JSONObject newStudent) {

		MongoCollection<Document> coursesCollection = mydatabase.getCollection("Courses");

		// update the course
		Document course = new Document("students",
				Arrays.asList(newStudent.getJSONObject("Student").getString("userName"), "", ""));
		coursesCollection.updateOne(eq("courseLevel", newStudent.getJSONObject("Student").getString("courseLevel")),
				new Document("$push", course));

		// update nb place
		MongoCollection<Document> collection = mydatabase.getCollection("Courses");
		Document courseDoc = collection
				.find(eq("courseLevel", newStudent.getJSONObject("Student").getString("courseLevel"))).first();
		int nbPlace = Integer.parseInt(courseDoc.get("NbPlace").toString()) - 1;

		Document nbPlaceDoc = new Document("NbPlace", nbPlace);

		System.out.println(nbPlaceDoc);
		courseDoc.put("NbPlace", String.valueOf(nbPlace));

		coursesCollection.replaceOne(eq("courseLevel", newStudent.getJSONObject("Student").getString("courseLevel")),
				courseDoc);

		return true;
	}

	@SuppressWarnings({ "unchecked", "null" })
	public boolean gradeStudent(JSONObject grade) {
		MongoCollection<Document> coursesCollection = mydatabase.getCollection("Courses");

		// query to find the right course
		String[] splitCourseCode = grade.getString("courseCode").split("-", 2);

		BasicDBObject andQuery = new BasicDBObject();
		List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
		obj.add(new BasicDBObject("courseLevel", splitCourseCode[1]));
		obj.add(new BasicDBObject("sessionCode", splitCourseCode[0]));
		// obj.add(new BasicDBObject("students.0.0",
		// new BasicDBObject("", grade.getJSONObject("Student").getString("name"))));
		andQuery.put("$and", obj);

		System.out.println("gradeStudentQuery : " + andQuery.toString());

		Document documentCourse = coursesCollection.find(andQuery).first();
		// MongoCursor<Document> cursor = iterable.iterator();

		ArrayList<Object> studentsList = (ArrayList<Object>) documentCourse.get("students");
		System.out.println(studentsList.toString());

		int index = 99999;
		for (int i = 0; i < studentsList.size(); i++) {
			ArrayList<String> student = (ArrayList<String>) studentsList.get(i);

			System.out.println(student.toString());
			if (student.get(0).equals(grade.getJSONObject("Student").getString("name"))) {
				index = i;
			}
		}
		System.out.println("indexArray : " + index);

		BasicDBObject gradingStudent = new BasicDBObject();
		gradingStudent.put("students." + index + ".1", grade.getJSONObject("Student").getString("comments"));
		gradingStudent.put("students." + index + ".2", grade.getJSONObject("Student").getString("grade"));

		BasicDBObject updateGrade = new BasicDBObject();
		updateGrade.put("$set", gradingStudent);

		coursesCollection.updateOne(andQuery, updateGrade);

		return true;

	}

	@SuppressWarnings("unchecked")
	public ArrayList<Object> getGradesForCourse(String courseCode) {
		MongoCollection<Document> courseCollection = mydatabase.getCollection("Courses");

		String[] splitCourseCode = courseCode.split("-", 2);

		BasicDBObject andQuery = new BasicDBObject();
		List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
		obj.add(new BasicDBObject("courseLevel", splitCourseCode[1]));
		obj.add(new BasicDBObject("sessionCode", splitCourseCode[0]));
		andQuery.put("$and", obj);

		System.out.println("getGradesQuery : " + andQuery.toString());

		Document course = courseCollection.find(andQuery).first();

		ArrayList<Object> studentsGrades = null;
		studentsGrades = (ArrayList<Object>) course.get("students");

		return studentsGrades;

	}

}
