package com.webapi.services;

import java.text.ParseException;
import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.pronatation.Session.SessionDTO;

import com.webapi.mangodb.DatabaseManager;

public class SessionService {

	DatabaseManager dbManager;
	MongoClient client;
	MongoDatabase mydatabase;

	public SessionService() {

		dbManager = new DatabaseManager();
		client = dbManager.connect();
		mydatabase = dbManager.getDatabase(client);

	}

	public ArrayList<SessionDTO> getAllSessions() throws ParseException {

		MongoCollection<Document> sessionCollection = mydatabase.getCollection("Sessions");

		FindIterable<Document> iterable = sessionCollection.find(); // (1)

		MongoCursor<Document> cursor = iterable.iterator(); // (2)

		ArrayList<SessionDTO> sessionsList = new ArrayList<SessionDTO>();

		try {

			while (cursor.hasNext()) {

				Document session = cursor.next();

				SessionDTO sessionDTO = new SessionDTO(session.getString("sessionCode"), session.getString("season"),
						session.getString("year"), session.getString("sessionFrom"), session.getString("sessionTo"));
				sessionsList.add(sessionDTO);
			}

		} finally {

			cursor.close();

		}

		return sessionsList;

	}

	public boolean createSession(SessionDTO newSession) {

		MongoCollection<Document> sessionCollection = mydatabase.getCollection("Sessions");

		Document session = new Document("sessionCode", newSession.getCode()).append("season", newSession.getSeason())
				.append("year", newSession.getYear()).append("sessionFrom", newSession.getSessionFrom())
				.append("sessionTo", newSession.getSessionTo());

		sessionCollection.insertOne(session);

		return true;

	}

}