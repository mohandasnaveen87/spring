package com.example.model;

import java.io.Serializable;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Component
//@JsonIgnoreProperties(value= {"personId","lastName"})
@JsonFilter(value = "PersonFilter")
public class PersonModel extends  RepresentationModel<PersonModel> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3162457022367069250L;
	
	private  String personId;

	private  String lastName;
	private CollectionModel<DependentModel> dependentModel;

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

	public CollectionModel<DependentModel> getDependentModel() {
		return dependentModel;
	}

	public void setDependentModel(CollectionModel<DependentModel> collectionModel) {
		this.dependentModel = collectionModel;
	}

	
	

	
}
