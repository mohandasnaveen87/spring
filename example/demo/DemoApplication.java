package com.example.demo;

import java.util.List;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UriComponentsBuilder;

@SpringBootApplication
@RestController
@ComponentScan(basePackages = "com.example.*")
@EnableJpaRepositories(basePackages="com.example.repository")
public class DemoApplication implements WebMvcConfigurer{
	
	@Autowired
	private DataSource dataSource;
	@Autowired
    private JdbcTemplate jdbcTemplateObject;

	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	    this.jdbcTemplateObject = new JdbcTemplate(dataSource);

	}
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
@Override
public void addResourceHandlers(ResourceHandlerRegistry registry) {
	// TODO Auto-generated method stub
	registry.addResourceHandler("swagger-ui.html")
    .addResourceLocations("classpath:/META-INF/resources/");

    registry.addResourceHandler("/webjars/**")
    .addResourceLocations("classpath:/META-INF/resources/webjars/");
}
	/*
	 * @RequestMapping(value = "/") public String hello() { return "Hello World"; }
	 */
	@RequestMapping(value = "/showPage")
	   /*public ModelAndView helloWithView(@RequestParam("id") int id) {*/
	public ModelAndView helloWithView(@ModelAttribute("person") Person person) {
		System.out.println(dataSource);
		System.out.println(jdbcTemplateObject.getDataSource());
		
		 //String SQL = "select * from persons";
		String SQL="SELECT person_id, last_name FROM persons ORDER BY person_id "+
        "OFFSET "+person.getPersonId()+" ROWS FETCH NEXT 20 ROWS ONLY";
		 List<Person> persons = jdbcTemplateObject.query(SQL, new PersonMapper());
		 
		 for(Person p:persons) { System.out.println(p.getPersonId()+
		 " "+p.getLastName()+"\n"); }
		 
		 persons=persons.stream().filter(x->x.getLastName().equalsIgnoreCase("Rosario")).collect(Collectors.toList());
		 
		 ModelAndView mv=new ModelAndView();
		 mv.addObject("personList", persons);
		 mv.setViewName("success");
		// mv.addObject("person", new Person());
		 
		  return mv;
	   }
	/*
	 * public List<Person> listStudents() { String SQL = "select * from persons";
	 * List<Person> persons = jdbcTemplateObject.query(SQL, new PersonMapper());
	 * return persons; }
	 * 	
	 */
	@RequestMapping(value = "/showPageAsync")
	   public @ResponseBody List<Person> helloWithResBody(@RequestParam("id") int id) {
		String SQL="SELECT personid, lastname FROM persons ORDER BY personid "+
		        "OFFSET "+id+" ROWS FETCH NEXT 20 ROWS ONLY";
				 List<Person> persons = jdbcTemplateObject.query(SQL, new PersonMapper());
				 
				 for(Person p:persons)
				 { 
					 System.out.println(p.getPersonId()+
				 " "+p.getLastName()+"\n"); }
				 return persons;
	}
	@RequestMapping(value = "/insertPerson",method=RequestMethod.POST)
	   public ModelAndView postPerson(Person person) {
			
		String name=person.getLastName();
		String SQL="insert into persons(personid,lastname) values("+person.getPersonId()+","+"'"+person.getLastName()+"'"+")";
				 int rows = jdbcTemplateObject.update(SQL);
				 ModelAndView mv=new ModelAndView();
				 mv.setViewName("success");
				 return mv;
				 
	}
	@RequestMapping(value = "/addPerson",method=RequestMethod.POST)
	   public @ResponseBody String addPerson(Person person) {
		
		String name=person.getLastName();
		String SQL="insert into persons(personid,lastname) values("+person.getPersonId()+","+"'"+person.getLastName()+"'"+")";
				 int rows = jdbcTemplateObject.update(SQL);
				
				 return rows+" "+"rows inserted";
	}
	@RequestMapping(value = "/addPersonLocation",method=RequestMethod.POST)
	   public ResponseEntity<Void> addPersonLocation(Person person) {
		
		String name=person.getLastName();
		String SQL="insert into persons(personid,lastname) values("+person.getPersonId()+","+"'"+person.getLastName()+"'"+")";
				 int rows = jdbcTemplateObject.update(SQL);
				 HttpHeaders headers = new HttpHeaders();
				 headers.setLocation(UriComponentsBuilder.fromHttpUrl("http://localhost:8080/getPerson").queryParam("id", person.getPersonId()).build().toUri());
				 return new ResponseEntity<Void>(headers,HttpStatus.CREATED);
	}
	@RequestMapping(value = "/insertPersonJson",method=RequestMethod.POST)
	   public ResponseEntity<Object> postPersonJson(@RequestBody Person person) {
		
		String name=person.getLastName();
		System.out.println(name);
		String SQL="insert into persons(personid,lastname) values("+person.getPersonId()+","+"'"+person.getLastName()+"'"+")";
				 int rows = jdbcTemplateObject.update(SQL);
				 //ModelAndView mv=new ModelAndView();
				 //mv.setViewName("success");
				 Person per =new Person();
					per.setLastName(name);
					per.setPersonId(person.getPersonId());
				 return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	/*
	 * @Override public void addResourceHandlers(ResourceHandlerRegistry registry) {
	 * 
	 * // Register resource handler for images
	 * registry.addResourceHandler("/images/**").addResourceLocations(
	 * "classpath:/resources/images/") .setCacheControl(CacheControl.maxAge(2,
	 * TimeUnit.HOURS).cachePublic()); }
	 */
	@RequestMapping(value = "/showGetRequest")
	public ResponseEntity<Object> getPersons(@RequestParam("id") String id){
		Person per =new Person();
		per.setLastName("Naveen");
		per.setPersonId(id);
		return new ResponseEntity<Object>(per, HttpStatus.OK);
		
	}
	@RequestMapping(value = "/showPersonsList",method=RequestMethod.GET )
	   public @ResponseBody List<Person> getAllPersons() {
		String SQL="SELECT person_id, last_name FROM persons";
				 List<Person> persons = jdbcTemplateObject.query(SQL, new PersonMapper());
				 
				 return persons;
	}
	@RequestMapping(value = "/getPerson")
	public @ResponseBody List<Person> getPersonById(@RequestParam("id") String id){
		String SQL="SELECT personid,lastname FROM persons where personid="+id;
		 List<Person> persons = jdbcTemplateObject.query(SQL, new PersonMapper());
		 
		 return persons;
		
	}
	@PutMapping(value = "/getPerson/{personId}")
	public ResponseEntity<Object> updatePerson(@PathVariable String personId,@RequestBody Person person){
		String SQL="SELECT personid,lastname FROM persons where personid="+personId;
		 List<Person> persons = jdbcTemplateObject.query(SQL, new PersonMapper());
		 if(persons.size()>0) {
			 persons.get(0).setLastName(person.getLastName());
			 String updateQry="update persons set lastName="+"'"+persons.get(0).getLastName()+"'"+" where personid="+personId;
			 int rows = jdbcTemplateObject.update(updateQry);
		 }
		 return new ResponseEntity<Object>(HttpStatus.OK) ;
		
	}
}
	