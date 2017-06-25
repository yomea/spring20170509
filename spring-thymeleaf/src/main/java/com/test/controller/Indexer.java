package com.test.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.test.entity.User;
import com.test.service.IndexService;

@Controller
public class Indexer {
	
	@Autowired
	private IndexService service;
	
	@RequestMapping(value="/")
	public String index(HttpServletRequest request,Model model, HttpSession session, MultipartFile[] files) {
		
		model.addAttribute("user", new User(1, "root"));
		
		session.setAttribute("test", new User(3, "hello!"));
		
		model.addAttribute("str", "hello world");
		
		
		List<User> users = service.findAll();
		
		model.addAttribute("users", users);
		
		
		
		
		
		return "thymeleaf";
	}
	
}
