package com.rabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class NewTask {

	private final static String QUEUE_NAME = "hello"; 

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

		/**
		 * 定义一个队列 queueDeclare(String queue,//队列的名字 boolean durable,
		 * //如果为true，那么等到服务器重启，这个队列才生效 boolean exclusive, //如果为true，表示排他队列
		 * boolean autoDelete,//当队列不再使用的时候就自动删除 Map<String, Object>
		 * arguments)//队列的构造参数
		 */
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);

		String message = getMessage(args);
		/**
		 * Parameters: exchange: the exchange to publish the message to交换发送信息
		 * routingKey: the routing key 路由key，这里就是队列的名字，表示要发送到这个队列上 props: other
		 * properties for the message - routing headers etc//这个信息的属性 body: the
		 * message body 要发送的信息
		 */
		channel.basicPublish("", "hello", null, message.getBytes());
		
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
