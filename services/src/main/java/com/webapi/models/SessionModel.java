package com.webapi.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SessionModel {

	private String code = "";
	private String season = "";
	private String year = "";
	public SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private Date sessionFrom = new Date();
	private Date sessionTo = new Date();

	public SessionModel() {
	}

	public SessionModel(String code, String season, String year, String dateFrom, String dateTo) throws ParseException {

		this.code = code;
		this.season = season;
		this.year = year;
		this.sessionFrom = sdf.parse(dateFrom);
		this.sessionTo = sdf.parse(dateTo);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getSessionFrom() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String stringDate = sdf.format(this.sessionFrom);
		return stringDate;
	}

	public void setSessionFrom(String dateFrom) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		this.sessionFrom = sdf.parse(dateFrom);
	}

	public String getSessionTo() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String stringDate = sdf.format(this.sessionTo);
		return stringDate;
	}

	public void setSessionTo(String dateTo) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		this.sessionTo = sdf.parse(dateTo);
	}

}
