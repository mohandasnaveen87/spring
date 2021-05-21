package com.example.model;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Component;
@Component
public class DependentModel extends  RepresentationModel<PersonModel> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2544186892001367370L;
	 private String dependentId;
	  private String age;
	  private String firstName;
	  private String lastName;
	 private PersonModel personModel;
	public String getDependentId() {
		return dependentId;
	}
	public void setDependentId(String dependentId) {
		this.dependentId = dependentId;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public PersonModel getPersonModel() {
		return personModel;
	}
	public void setPersonModel(PersonModel personModel) {
		this.personModel = personModel;
	}
	
		


}
