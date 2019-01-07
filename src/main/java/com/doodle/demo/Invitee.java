package com.doodle.demo;

import org.springframework.data.mongodb.core.mapping.Field;

public class Invitee {
	private String name;
	@Field("id") 
	private int id;
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
	
	
}
