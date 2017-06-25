package com.spring_thymeleaf.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.spring_thymeleaf.entity.User;

@Controller
public class Thymeleaf_003 {
	
	
	@GetMapping("thymeleaf_003")
	public String index(Model model, HttpSession session) {
		
		User user = new User(1, "root");
		
		model.addAttribute("zhanweifu", "hehe");
		
		model.addAttribute("user", user);
		
		session.setAttribute("user", user);
		session.setAttribute("test", "test");
		
		return "thymeleaf_003";
	}

}
