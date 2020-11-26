package com.webapi.models;

public class Course {

	private String courseCode = "";
	private String sessionCode = "";
	private String courseLevel = "";
	private String description = "";
	private String NbPlace = "";
	private String price = "";
	private Object[] prerequisite = null;

	public Course(String courseLevel, Object[] prerequisite) {
		this.courseLevel = courseLevel;
		this.prerequisite = prerequisite;
	}

	public Course(String sessionCode, String courseLevel, String description, String NbPlace, String price,
			Object[] prerequisite) {
		this.sessionCode = sessionCode;
		this.courseLevel = courseLevel;
		this.description = description;
		this.NbPlace = NbPlace;
		this.price = price;
		this.prerequisite = prerequisite;
	}

	public Course(String sessionCode, String courseLevel) {
		this.sessionCode = sessionCode;
		this.courseLevel = courseLevel;

	}

	public Course(String courseCode, String sessionCode, String courseLevel) {
		this.courseCode = courseCode;
		this.sessionCode = sessionCode;
		this.courseLevel = courseLevel;
	}

	public Course(String sessionCode, String courseLevel, String description, String NbPlace, String price) {
		this.sessionCode = sessionCode;
		this.courseLevel = courseLevel;
		this.description = description;
		this.NbPlace = NbPlace;
		this.price = price;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getCourseLevel() {
		return courseLevel;
	}

	public void setCourseLevel(String courseLevel) {
		this.courseLevel = courseLevel;
	}

	public String getSessionCode() {
		return sessionCode;
	}

	public void setSessionCode(String sessionCode) {
		this.sessionCode = sessionCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNbPlace() {
		return NbPlace;
	}

	public void setNbPlace(String nbPlace) {
		NbPlace = nbPlace;
	}

	public String getprice() {
		return this.price;
	}

	public void setprice(String price) {
		this.price = price;
	}

	public Object[] getPrerequisite() {
		return prerequisite;
	}

	public void setPrerequisite(Object[] prerequisite) {
		this.prerequisite = prerequisite;
	}

}
