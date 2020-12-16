package com.webapi.services;

import java.text.ParseException;
import java.util.ArrayList;

import org.bson.Document;
import org.json.JSONObject;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.webapi.mangodb.DatabaseManager;
import com.webapi.models.SessionModel;

public class SessionService {

	DatabaseManager dbManager;
	MongoClient client;
	MongoDatabase mydatabase;

	public SessionService() {

		dbManager = new DatabaseManager();
		client = dbManager.connect();
		mydatabase = dbManager.getDatabase(client);

	}

	public ArrayList<SessionModel> getAllSessions() throws ParseException {

		MongoCollection<Document> sessionCollection = mydatabase.getCollection("Sessions");

		FindIterable<Document> iterable = sessionCollection.find(); // (1)

		MongoCursor<Document> cursor = iterable.iterator(); // (2)

		ArrayList<SessionModel> sessionsList = new ArrayList<SessionModel>();

		try {

			while (cursor.hasNext()) {

				Document session = cursor.next();
				System.out.println(session.toString());

				SessionModel sessionModel = new SessionModel();
				sessionModel = new SessionModel(session.getString("sessionCode"), session.getString("season"),
						session.getString("year"), session.getString("sessionFrom"), session.getString("sessionTo"));
				sessionsList.add(sessionModel);
			}

		} finally {

			cursor.close();

		}

		return sessionsList;

	}

	public boolean createSession(JSONObject newSession) {

		MongoCollection<Document> sessionCollection = mydatabase.getCollection("Sessions");

		Document session = new Document("sessionCode", newSession.getString("sessionCode"))
				.append("season", newSession.getString("season")).append("year", newSession.getString("year"))
				.append("sessionFrom", newSession.getString("sessionFrom"))
				.append("sessionTo", newSession.getString("sessionTo"));

		sessionCollection.insertOne(session);

		return true;

	}

}