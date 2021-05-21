package com.example.demo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedEntityGraph;
import javax.persistence.Table;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.annotation.JsonBackReference;
@Component
@Entity
@NamedEntityGraph(name = "dependentOnly")
@Table
public class Dependent extends RepresentationModel<Dependent> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5802209412406230452L;
	@Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
  private String dependentId;
	@Column
  private String age;
	@Column
  private String firstName;
	@Column
  private String lastName;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="person_id")
	@JsonBackReference
 private Person person;
	
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
public Person getPerson() {
	return person;
}
public void setPerson(Person person) {
	this.person = person;
}
  
  
}
