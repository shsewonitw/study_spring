package com.tje.model;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class PersonWithCatResource {
	private String name;

	@Resource(name = "cat")
	private Pet pet;

	
	public PersonWithCatResource() {
	}

	public PersonWithCatResource(String name, Pet pet) {
		this.name = name;
		this.pet = pet;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}
	
	
}
