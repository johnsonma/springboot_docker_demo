package com.doodle.demo;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Field;

public class Participant {
	private String name;
	@Field("id") 
	private int id;
	private List<Integer> preferences;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Integer> getPreferences() {
		return preferences;
	}
	public void setPreferences(List<Integer> preferences) {
		this.preferences = preferences;
	}
	
	
	
	
}
