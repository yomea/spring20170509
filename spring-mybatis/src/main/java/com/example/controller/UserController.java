package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Person;
import com.example.entity.User;
import com.example.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService service;
	
	@GetMapping("/users")
	public List<User> findAll() {
		
		
		return service.findAll();
	}
	
	@GetMapping("/persons")
	public List<Person> findPerson() {
		
		
		return service.findPerson();
	}
	
	@GetMapping("/save")
	public String savePerson() {
		
		Person person = new Person();
		person.setId(null);
		person.setUsername("youth");
		person.setAge(23);
		
		service.savePerson(person);
		return "ok!";
	}

}
