package com.example.dao.impl;

import org.springframework.stereotype.Component;

import com.example.dao.HelloRemote;

@Component
public class HelloRemoteHystrix implements HelloRemote {

	@Override
	public String hello(String name) {
		
		return "fail";
	}

}
