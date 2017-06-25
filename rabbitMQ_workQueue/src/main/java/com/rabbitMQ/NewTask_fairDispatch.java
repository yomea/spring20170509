package com.rabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class NewTask_fairDispatch {

	private final static String QUEUE_NAME = "task_queue"; 

	public static void main(String[] args) throws Exception {
		// 创建连接工厂
		ConnectionFactory factory = new ConnectionFactory();
		// 设置连接rabbitMQ服务器的ip
		factory.setHost("localhost");
		// factory.setPort(5672);
		// 创建一个连接到服务器的链接
		Connection connection = factory.newConnection();
		// 创建连接通道
		Channel channel = connection.createChannel();

		
		boolean durable = true;
		channel.queueDeclare(QUEUE_NAME, durable, false, false, null);

		String message = getMessage(args);
		
		//将队列中的信息定义为可持久化的纯文本
		channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
		
		System.out.println(" [x] Sent '" + message + "'");

		channel.close();

		connection.close();
	}

	private static String getMessage(String[] strings) {
		if (strings.length < 1)
			return "Hello World!";
		return joinStrings(strings, " ");
	}

	private static String joinStrings(String[] strings, String delimiter) {
		int length = strings.length;
		if (length == 0)
			return "";
		StringBuilder words = new StringBuilder(strings[0]);
		for (int i = 1; i < length; i++) {
			words.append(delimiter).append(strings[i]);
		}
		return words.toString();
	}

}
