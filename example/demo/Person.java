package com.example.demo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonManagedReference;
@Component
@Entity
@Table(name="persons")
public class Person  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2276081075214616756L;

	@Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="personId")
	private  String personId;
	
	@Column(name="lastName")
	private  String lastName;
	public Person() {
		super();
	}
	public Person(String personId, String lastName, List<Dependent> dependent) {
		super();
		this.personId = personId;
		this.lastName = lastName;
		this.dependent = dependent;
	}
	@OneToMany(cascade=CascadeType.PERSIST,mappedBy="person",fetch = FetchType.LAZY,orphanRemoval = true)
	@JsonManagedReference
	private List<Dependent> dependent;
	
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
	public List<Dependent> getDependent() {
		return dependent;
	}
	public void setDependent(List<Dependent> dependent) {
		this.dependent = dependent;
	}
	
}
