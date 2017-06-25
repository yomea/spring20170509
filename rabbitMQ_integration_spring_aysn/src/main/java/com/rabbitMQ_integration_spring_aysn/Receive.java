package com.rabbitMQ_integration_spring_aysn;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Receive {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		 new ClassPathXmlApplicationContext("applicationContext-rabbitmq-async-receive.xml");
	}
}