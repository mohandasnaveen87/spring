package com.example.demo;

import java.util.HashSet;
import java.util.Set;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Component;

import com.example.model.PersonModel;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@Component
public class PersonFilter {
	

	public MappingJacksonValue filterFields(PersonModel personModel){
		Set<String> pset=new HashSet<String>();
		pset.add("personId");
		//SimpleBeanPropertyFilter beanFilter=new SimpleBeanPropertyFilter.FilterExceptFilter(pset);
		SimpleBeanPropertyFilter beanFilter=new SimpleBeanPropertyFilter.SerializeExceptFilter(pset);
		FilterProvider filters=new SimpleFilterProvider().addFilter("PersonFilter", beanFilter);
		
		MappingJacksonValue mapping=new MappingJacksonValue(personModel);
		mapping.setFilters(filters);
		
		return  mapping;
	}
}
