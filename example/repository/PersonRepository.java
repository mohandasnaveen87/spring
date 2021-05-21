package com.example.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Person;
@Repository
public interface PersonRepository extends CrudRepository<Person,String> {

	List<Person> findByLastName(String lastName);
	List<Person> findDistinctByLastName(String lastName);
	
}
