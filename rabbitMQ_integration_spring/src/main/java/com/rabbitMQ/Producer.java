package com.rabbitMQ;

/**
 * rabbitMQ与Spring整合之同步消息
 */
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//生产者
public class Producer {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		AmqpTemplate amqpTemplate = context.getBean(RabbitTemplate.class);  
		//Convert a Java object to an Amqp Message and send it to a default exchange with a default routing key.
		amqpTemplate.convertAndSend("test spring sync"); 
	}
}