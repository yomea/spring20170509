package com.spring_thymeleaf.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Indexer {
	
	@GetMapping(value="/")
	public String index() {
		
		
		return "thymeleaf test";
	}
	
}
