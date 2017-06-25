package com.rabbitMQ;

import java.io.IOException;
import java.util.HashMap;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class Consumer {

	private static String queue_name = "message_ttl_queue";

	public static void main(String[] args) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		factory.setUsername("root");
		factory.setPassword("root");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		HashMap<String, Object> arguments = new HashMap<String, Object>();
		//告知这个队列消息到期时应该发完那个转发器
		arguments.put("x-dead-letter-exchange", "amq.direct");
		//当这个队列中的某个消息到期，队列需要告知转发器此消息应当发往哪个路由，此时这个队列相当于一个生产者
		arguments.put("x-dead-letter-routing-key", "message_ttl_routingKey");
		channel.queueDeclare("delay_queue", true, false, false, arguments);

		// 声明队列
		channel.queueDeclare(queue_name, true, false, false, null);
		// 绑定路由
		channel.queueBind(queue_name, "amq.direct", "message_ttl_routingKey");

		
		com.rabbitmq.client.Consumer consumer = new DefaultConsumer(channel) {

			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				
				String message = new String(body);
				System.out.println("received message:" + message + ",date:" + System.currentTimeMillis());
				
				
			}
			
			
			
		};
		
		// 指定消费队列
		channel.basicConsume(queue_name, true, consumer);
	}

}
