package com.example.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.example.controller.PersonController;
import com.example.demo.Dependent;
import com.example.model.DependentModel;
@Component
public class DependentModelAssembler extends RepresentationModelAssemblerSupport<Dependent,DependentModel>{ 

	public DependentModelAssembler() {
		super(PersonController.class,DependentModel.class);
	}
@Autowired
PersonModelAssembler personModelAssembler;
	@Override
	public DependentModel toModel(Dependent entity) {
		// TODO Auto-generated method stub
		DependentModel dependentModel=instantiateModel(entity);
		/*
		 * Link selflink =
		 * linkTo(methodOn(PersonController.class).getPersonById(entity.getPersonId()))
		 * .withSelfRel(); Link deplink =
		 * linkTo(methodOn(PersonController.class).getPersonDependents(entity.
		 * getPersonId())) .withRel("dependents"); personModel.add(selflink,deplink);
		 */
		dependentModel.setDependentId(entity.getDependentId()); 
		dependentModel.setLastName(entity.getLastName());
		dependentModel.setFirstName(entity.getFirstName());
		dependentModel.setAge(entity.getAge());
		//dependentModel.setPersonModel(personModelAssembler.toModel(entity.getPerson()));
		dependentModel.add(linkTo(methodOn(PersonController.class).getPersonDependents(entity.getPerson().getPersonId())).slash(entity.getDependentId()).withSelfRel());
		// TODO Auto-generated method stub
		return dependentModel;
	}
	@Override
	public CollectionModel<DependentModel> toCollectionModel(Iterable<? extends Dependent> entities) {
		// TODO Auto-generated method stub
		
		  CollectionModel<DependentModel> depModel=super.toCollectionModel(entities);
		  System.out.println(depModel);
		  
		/*
		 * depModel.forEach(dep->dep.add(linkTo(methodOn(PersonController.class).
		 * getPersonDependents(dep.getPerson().getPersonId())).slash(dep.getDependentId(
		 * )) .withSelfRel()));
		 */
		 
		 
		return depModel;
	}
}
