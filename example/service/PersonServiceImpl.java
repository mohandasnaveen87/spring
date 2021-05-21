package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Dependent;
import com.example.demo.Person;
import com.example.repository.PersonRepository;
@Service
public class PersonServiceImpl implements IPersonService{
     @Autowired
     PersonRepository personRepos;
     

     @Autowired
     private Dependent dependent;
     
	@Override
	public List<Person> findAllpersons() {
		// TODO Auto-generated method stub
		
		return (List<Person>) personRepos.findAll();
	}
	@Override
	public Optional<Person> findPersonById(String Id) {
		// TODO Auto-generated method stub
		return personRepos.findById(Id);
	}
	@Override
	public List<Person> findPersonByLastName(String lastName) {
		// TODO Auto-generated method stub
		
		return personRepos.findByLastName(lastName) ;
	}
	@Override
	public Person findUniquePersonByLastName(String lastName) {
		String lastName=personRepos.findDistinctByLastName(lastName).stream().findAny().get();
		// TODO Auto-generated method stub
				String concatName=lastName+"Zulfiqar";
		return concatName;
	}
	@Override
	public Person savePerson(Person person) {
		// TODO Auto-generated method stub
		//dependent.setPerson(person);
		
		person.getDependent().forEach(per->per.setPerson(person));
		return personRepos.save(person);
	}
	@Override
	public void deletePerson(String Id) {
		
		personRepos.deleteById(Id);
		return;
	}
	

}
