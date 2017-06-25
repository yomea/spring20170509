package com.example.service;

import java.util.List;

import com.example.entity.Person;
import com.example.entity.User;

public interface UserService {
	
	List<User> findAll();
	List<Person> findPerson();
	
	void savePerson(Person person);
		
}
