package com.rabbitMQ;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.rabbitMQ.configuration.AnnotationConfiguration;

public class Consumer {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(AnnotationConfiguration.class);

		//也可以在这声明新的队列 声明一个队列
	/*	AmqpAdmin amqpAdmin = context.getBean(AmqpAdmin.class);
		Queue helloWorldQueue = new Queue("create.world.queue");
		amqpAdmin.declareQueue(helloWorldQueue);*/

		AmqpTemplate amqpTemplate = context.getBean(AmqpTemplate.class);
		System.out.println("Received: " + amqpTemplate.receiveAndConvert());
	}
}