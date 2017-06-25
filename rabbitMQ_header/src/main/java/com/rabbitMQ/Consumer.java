package com.rabbitMQ;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class Consumer {
	private final static String EXCHANGE_NAME = "headers";
	private final static String QUEUE_NAME = "header-queue";

	public static void main(String[] args) throws Exception {
		// 创建连接和频道
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		// 指定用户 密码
		// factory.setUsername("admin");
		// factory.setPassword("admin");
		// 指定端口
		factory.setPort(AMQP.PROTOCOL.PORT);
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		// 声明转发器和类型headers
		channel.exchangeDeclare(EXCHANGE_NAME, "headers");
		channel.queueDeclare(QUEUE_NAME, false, false, true, null);

		Map<String, Object> headers = new Hashtable<String, Object>();
		headers.put("x-match", "any");// all any匹配任意一个键值对
		headers.put("aaa", "01234");
		headers.put("bbb", "56789");
		// 为转发器指定队列，设置binding 绑定header键值对
		channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "", headers);
		com.rabbitmq.client.Consumer consumer = new DefaultConsumer(channel) {

			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {

				String message = new String(body, "UTF-8");
				System.out.println(message);
			}

		};
		// 指定接收者，第二个参数为自动应答，无需手动应答
		channel.basicConsume(QUEUE_NAME, true, consumer);

	}

}
