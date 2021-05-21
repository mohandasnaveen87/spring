package com.example.domain;

import java.io.Serializable;

import org.springframework.stereotype.Component;
@Component
public class Person implements Serializable {

	private  String personId;
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	private  String lastName;
}
