package com.spring_thymeleaf.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring_thymeleaf.entity.User;

public interface IUserDao extends JpaRepository<User, Integer> {
	
	public User findByUsername(String username);
	
	
	
}
