package com.rabbitMQ;

import java.util.Calendar;
import java.util.Hashtable;
import java.util.Map;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.AMQP.BasicProperties.Builder;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer {
	private final static String EXCHANGE_NAME = "headers";
	
	public static void main(String[] args) throws Exception {
		// 创建连接和频道
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		// 指定用户 密码
//		factory.setUsername("admin");
//		factory.setPassword("admin");
		// 指定端口
		factory.setPort(AMQP.PROTOCOL.PORT);
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		
		//声明转发器和类型headers
		channel.exchangeDeclare(EXCHANGE_NAME, "headers");
		String message = Calendar.getInstance() + " : log something";
		
		Map<String,Object> headers =  new Hashtable<String, Object>();
		headers.put("aaa", "01234");
		Builder properties = new BasicProperties.Builder();
		properties.headers(headers);
		
		// 指定消息发送到的转发器,绑定键值对headers键值对
		channel.basicPublish(EXCHANGE_NAME, "",properties.build(),message.getBytes());
		
		System.out.println("Sent message :'" + message + "'");
		channel.close();
		connection.close();
	}
}

