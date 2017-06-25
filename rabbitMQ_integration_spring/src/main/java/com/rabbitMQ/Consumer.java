package com.rabbitMQ;

/**
 * rabbitMQ与spring整合之同步消息
 */
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//消费者
public class Consumer {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		AmqpTemplate amqpTemplate = context.getBean(RabbitTemplate.class);  
		System.out.println("Received: " + amqpTemplate.receiveAndConvert());  
	}
}