package org.demo.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Category {
	
	@Id
	long id;
	
	String name;


	public Category() {
		super();
	}	
	
	public Category(long id, String name) {
		this();
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
	
}
