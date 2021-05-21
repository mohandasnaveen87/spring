package com.example.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.Person;

public interface IPersonService {
 List<Person> findAllpersons();
 Optional<Person> findPersonById(String personId);
 List<Person> findPersonByLastName(String lastName);
 Person findUniquePersonByLastName(String lastName);
 Person savePerson(Person person);
 void deletePerson(String Id);
 
}
