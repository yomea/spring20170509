package com.quartz.entity;

import org.springframework.stereotype.Component;

@Component("myBean")
public class MyBean {
	
	public void printMessage() {
		
		System.out.println("printMessage tell you, you success!!!");
		
	}
	

}
