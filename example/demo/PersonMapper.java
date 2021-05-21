package com.example.demo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class PersonMapper implements RowMapper<Person> {
   public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
	   Person person = new Person();
	   person.setPersonId(String.valueOf(rs.getInt("person_id")));
	   person.setLastName(rs.getString("last_name"));
      
      return person;
   }
}