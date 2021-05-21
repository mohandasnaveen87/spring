package com.example.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.afford;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.example.controller.PersonController;
import com.example.demo.Person;
import com.example.model.PersonModel;
@Component
public class PersonModelAssembler extends RepresentationModelAssemblerSupport<Person,PersonModel>{

	public PersonModelAssembler() {
		super(PersonController.class, PersonModel.class);
		// TODO Auto-generated constructor stub
	}
	@Autowired
    private DependentModelAssembler dependentModelAssembler;
	@Override
	public PersonModel toModel(Person entity) {
		
		PersonModel personModel=instantiateModel(entity);
		 Link selflink = linkTo(methodOn(PersonController.class).getPersonById(entity.getPersonId()))
		            .withSelfRel().andAffordance(afford(methodOn(PersonController.class).deletePersonById(entity.getPersonId())));  
		  Link deplink = linkTo(methodOn(PersonController.class).getPersonDependents(entity.getPersonId()))
		            .withRel("dependents");
		  personModel.add(selflink,deplink);
		  personModel.setPersonId(entity.getPersonId()); 
		  personModel.setLastName(entity.getLastName());
		  personModel.setDependentModel(dependentModelAssembler.toCollectionModel(entity.getDependent()));
		// TODO Auto-generated method stub
		return personModel;
	}
	@Override
	public CollectionModel<PersonModel> toCollectionModel(Iterable<? extends Person> entities) {
		// TODO Auto-generated method stub
		return super.toCollectionModel(entities);
	}

}
