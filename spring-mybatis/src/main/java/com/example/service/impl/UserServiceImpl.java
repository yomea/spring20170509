package com.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.UserDao;
import com.example.entity.Person;
import com.example.entity.User;
import com.example.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao dao;
	
	@Override
	public List<User> findAll() {
		
		return dao.findAll();
	}

	@Override
	public List<Person> findPerson() {
		
		return dao.findPerson();
	}

	@Override
	public void savePerson(Person person) {
		dao.savePerson(person);
		
	}

}
