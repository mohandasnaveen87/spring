
  package com.example.controller;
  
  import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import  org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.assembler.DependentModelAssembler;
import com.example.assembler.PersonModelAssembler;
import com.example.demo.Dependent;
import com.example.demo.Person;
import com.example.demo.PersonFilter;
import com.example.model.DependentModel;
import com.example.model.PersonModel;
import com.example.service.IPersonService;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
  
  @RestController
  public class PersonController {
      @Autowired
	  private IPersonService personService;
	/*
	 * @Autowired private IDependentService dependentService;
	 */
      @Autowired
      private PersonModelAssembler personModelAssembler;
      
      @Autowired
      private DependentModelAssembler dependentModelAssembler;
      
      @Autowired
      
      private PersonFilter personFilter;
      @Autowired
      private MessageSource messageSource;
       
  @RequestMapping(value = "/person",method=RequestMethod.GET) 
  public @ResponseBody List<Person> getAllPersons() {
	  
  return personService.findAllpersons();
  }
  
  @GetMapping(value = "/person/{personId}",params="version=1", produces=MediaTypes.HAL_FORMS_JSON_VALUE) 
  public ResponseEntity<MappingJacksonValue> getPersonByIdParam(@PathVariable String personId) {
	 
	  
	return  personService.findPersonById(personId)
	  .map(personModelAssembler::toModel)
	  .map(personFilter::filterFields)
	  .map(ResponseEntity::ok)
	  .orElse(ResponseEntity.notFound().build());
  }
  @GetMapping(value = "/person/{personId}",params="version=2", produces=MediaTypes.HAL_FORMS_JSON_VALUE) 
  public ResponseEntity<MappingJacksonValue> getPersonById(@PathVariable String personId) {
	 
	  
	return  personService.findPersonById(personId)
	  .map(personModelAssembler::toModel)
	  .map(personFilter::filterFields)
	  .map(ResponseEntity::ok)
	  .orElse(ResponseEntity.notFound().build());
  }
  @GetMapping(value = "/person/{personId}",headers = "api-version=header") 
  public Person getPersonByIdHeader(@PathVariable String personId) {
	 
	  
	return new Person("30","koka",null);
  }
  @GetMapping(value = "/person/{personId}",produces="application/naveen+json") 
  public Person getPersonByIdProduces(@PathVariable String personId) {
	 
	  
	return new Person("30","Naveen",null);
  }
	/*
	 * @RequestMapping(value = "/person/filter/{personId}",method=RequestMethod.GET)
	 * public MappingJacksonValue getPersonByIdFilter(@PathVariable String personId)
	 * {
	 * 
	 * PersonModel
	 * personModel=personService.findPersonById(personId).map(personModelAssembler::
	 * toModel).get(); //personModel=personFilter.filterFields(personModel);
	 * Set<String> pset=new HashSet<String>(); pset.add("personId");
	 * SimpleBeanPropertyFilter beanFilter=new
	 * SimpleBeanPropertyFilter.FilterExceptFilter(pset); FilterProvider filters=new
	 * SimpleFilterProvider().addFilter("PersonFilter", beanFilter);
	 * 
	 * MappingJacksonValue mapping=new MappingJacksonValue(personModel);
	 * mapping.setFilters(filters);
	 * 
	 * return mapping; }
	 */  @RequestMapping(value = "/person/lastName/{lastName}",method=RequestMethod.GET) 
  public @ResponseBody Person getPersonByLastName(@PathVariable String lastName) {
	  //personService.findPersonByLastName(lastName);
	// try {
		// Person per=personService.findUniquePersonByLastName(lastName);
		 return personService.findUniquePersonByLastName(lastName);
	// }
		/*
		 * catch (Exception e) { // TODO: handle exception throw new
		 * UserNotFoundException("No user by this name"); }
		 */
	// System.out.println("person="+per);
		/*
		 * if(per==null) { throw new UserNotFoundException("No user by this name"); }
		 */
  
  }
  
  @RequestMapping(value = "/person",method=RequestMethod.POST) 
  public  ResponseEntity<Person> savePerson(@RequestBody Person person) {
	  //personService.findPersonByLastName(lastName);
	  personService.savePerson(person);
  return new ResponseEntity<Person>(HttpStatus.CREATED);
  }
  @RequestMapping(value = "/person/{personId}",method=RequestMethod.DELETE) 
  public  ResponseEntity<Person> deletePersonById(@PathVariable String personId) {
	  personService.deletePerson(personId);
  return new ResponseEntity<Person>(HttpStatus.OK);
  }
	
  
	  @RequestMapping(value ="/person/{personId}/dependents",method=RequestMethod.GET)
	  public  CollectionModel<DependentModel> getPersonDependents(@PathVariable String personId) 
	  {
	  
		 List<Dependent> dependIter=personService.findPersonById(personId).get().getDependent();
		/*
		 * dependentList.stream().forEach(dependentModelAssembler::toCollectionModel);
		 * .map(dependentModelAssembler::toCollectionModel)
		 * .collect(Collectors.toList());
		 */
	 // =dependentList;
	  //System.out.println("Size"+dependIter.);
	  return dependentModelAssembler.toCollectionModel(dependIter); }
	  @RequestMapping(value ="/person/message",method=RequestMethod.GET)
	  public String getMorningMessage() {
		  
		  return messageSource.getMessage("good.morning.message",null, LocaleContextHolder.getLocale());
	  }
	 
	    }
 
  