package com.quartz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

	// http://localhost:8989/quartz_spring_integration/index?name=root&password=123
	@RequestMapping("/index")
	@ResponseBody
	public String index(@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "password", required = false) String password) {

		return "the name is " + name + " the password is " + password;

	}

}
