package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexerController {

	@GetMapping("/index")
	public Object index() {
		
		
		return "hello";
	}
	
	
}
