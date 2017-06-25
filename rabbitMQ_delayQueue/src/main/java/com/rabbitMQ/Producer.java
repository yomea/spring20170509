package com.rabbitMQ;

import java.util.HashMap;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer {

	private static String queue_name = "message_ttl_queue";

	public static void main(String[] args) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		factory.setUsername("root");
		factory.setPassword("root");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		HashMap<String, Object> arguments = new HashMap<String, Object>();
		arguments.put("x-dead-letter-exchange", "amq.direct");
		arguments.put("x-dead-letter-routing-key", "message_ttl_routingKey");
		channel.queueDeclare("delay_queue", true, false, false, arguments);

		// 声明队列
		channel.queueDeclare(queue_name, true, false, false, null);
		// 绑定路由
		channel.queueBind(queue_name, "amq.direct", "message_ttl_routingKey");

		String message = "hello world!" + System.currentTimeMillis();
		// 设置延时属性
		AMQP.BasicProperties.Builder builder = new AMQP.BasicProperties.Builder();
		// 持久性 non-persistent (1) or persistent (2)expiration表示TTL单位ms
		AMQP.BasicProperties properties = builder.expiration("300000").deliveryMode(2).contentType("text/plain").build();
		// routingKey =delay_queue 进行转发
		channel.basicPublish("", "delay_queue", properties, message.getBytes());
		System.out.println("sent message: " + message + ",date:" + System.currentTimeMillis());
		// 关闭频道和连接
		channel.close();
		connection.close();
	}
}