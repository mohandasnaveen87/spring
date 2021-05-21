package com.example.client;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.domain.Person;

@SpringBootApplication
@RestController
@ComponentScan({"com.example.client","com.example.domain"})
public class ClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}
	@Bean
	   public RestTemplate getRestTemplate() {
	      return new RestTemplate();
	   }
	
	@Autowired
	   RestTemplate restTemplate;
	@Autowired
	   Person person;

	   @RequestMapping(value = "/persons")
	   public String getPersonsList() {
	      HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity <String> entity = new HttpEntity<String>(headers);
	      
	      return restTemplate.exchange("http://localhost:8080/showPersonsList", HttpMethod.GET, entity, String.class).getBody();
	   }
	   @RequestMapping(value = "/person")
	   public Person[] getPersonById(@RequestParam("id") int id) {
	      HttpHeaders headers = new HttpHeaders();
	     // headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
	      HttpEntity <String> entity = new HttpEntity<String>(headers);
	      UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/getPerson").queryParam("id", id);
	    //restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class).getBody();
	      //restTemplate.getForEntity(builder.toUriString(), String.class).getBody()
	      Person[] per=restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity,Person[].class).getBody();
	      
	      	return per;
	   }
	   @RequestMapping(value = "/addPerson",method=RequestMethod.POST)
	   public String postPerson(Person person) {
		   
		   HttpHeaders headers = new HttpHeaders();
		   headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		   MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
		   map.add("personId", person.getPersonId());
		   map.add("lastName", person.getLastName());
		   HttpEntity <MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(map,headers);
		   System.out.println(person.getPersonId()+"parameters"+ person.getLastName());
		   //restTemplate.exchange("http://localhost:8080/addPerson", HttpMethod.POST, entity,String.class).getBody()
		   return restTemplate.postForLocation("http://localhost:8080/addPersonLocation", entity).toString();
		//return restTemplate.postForEntity("http://localhost:8080/addPerson", entity, String.class).getBody();
		   
	   }
	   @RequestMapping(value = "/addPersonJson",method=RequestMethod.POST)
	   public String postPersonJson(@RequestBody Person person) {
		   HttpHeaders headers = new HttpHeaders();
		   headers.setContentType(MediaType.APPLICATION_JSON);
		   HttpEntity <Person> entity = new HttpEntity<Person>(person,headers);
		   return restTemplate.postForEntity("http://localhost:8080/insertPersonJson", entity, String.class).getStatusCode().toString();
	   }
}
