package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.HelloRemote;

@RestController
public class HelloController {
	
	@Autowired
	private HelloRemote hr;
	
	@RequestMapping("/hello/{name}")
	public String hello(@PathVariable("name") String name) {
		
		
		return hr.hello(name);
	}

}
