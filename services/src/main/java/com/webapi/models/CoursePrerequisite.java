package com.webapi.models;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoursePrerequisite {

	Map<String, List<String>> mapPrerequisite;

	public CoursePrerequisite() {
		mapPrerequisite = new HashMap<String, List<String>>();

		mapPrerequisite.put("Etoile_de_mer", Arrays.asList("none"));
		mapPrerequisite.put("Bambins", Arrays.asList("Etoile_de_mer"));
		mapPrerequisite.put("Tortues", Arrays.asList("Etoile_de_mer", "Bambins"));
		mapPrerequisite.put("Pingouins", Arrays.asList("Etoile_de_mer", "Bambins", "Tortues"));
		mapPrerequisite.put("Salamandre", Arrays.asList("Etoile_de_mer", "Bambins", "Tortues", "Pingouins"));
		mapPrerequisite.put("Baleines",
				Arrays.asList("Etoile_de_mer", "Bambins", "Tortues", "Pingouins", "Salamandre"));
		mapPrerequisite.put("Grenouilles",
				Arrays.asList("Etoile_de_mer", "Bambins", "Tortues", "Pingouins", "Salamandre", "Baleines"));
		mapPrerequisite.put("Dauphins", Arrays.asList("Etoile_de_mer", "Bambins", "Tortues", "Pingouins", "Salamandre",
				"Baleines", "Grenouilles"));
		mapPrerequisite.put("Junior1", Arrays.asList("Etoile_de_mer", "Bambins", "Tortues", "Pingouins", "Salamandre",
				"Baleines", "Grenouilles", "Dauphins"));
		mapPrerequisite.put("Junior2", Arrays.asList("Etoile_de_mer", "Bambins", "Tortues", "Pingouins", "Salamandre",
				"Baleines", "Grenouilles", "Dauphins", "Junior1"));
		mapPrerequisite.put("Junior3", Arrays.asList("Etoile_de_mer", "Bambins", "Tortues", "Pingouins", "Salamandre",
				"Baleines", "Grenouilles", "Dauphins", "Junior1", "Junior2"));
		mapPrerequisite.put("Junior4", Arrays.asList("Etoile_de_mer", "Bambins", "Tortues", "Pingouins", "Salamandre",
				"Baleines", "Grenouilles", "Dauphins", "Junior1", "Junior2", "Junior3"));
		mapPrerequisite.put("Junior5", Arrays.asList("Etoile_de_mer", "Bambins", "Tortues", "Pingouins", "Salamandre",
				"Baleines", "Grenouilles", "Dauphins", "Junior1", "Junior2", "Junior3", "Junior4"));
		mapPrerequisite.put("Maitre_Nageur", Arrays.asList("Age higher than 16"));
	}

	public List<String> getPrerequisiteOf(String courseLevel) {
		List<String> prerequisite = mapPrerequisite.get(courseLevel);
		System.out.println("Prerequisite pour " + courseLevel + " : " + prerequisite.toString());
		return prerequisite;
	}

}
