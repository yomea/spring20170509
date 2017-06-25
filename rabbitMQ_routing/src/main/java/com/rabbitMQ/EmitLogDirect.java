package com.rabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class EmitLogDirect {

	private static final String EXCHANGE_NAME = "direct_logs";

	public static void main(String[] argv) throws java.io.IOException, Exception {

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.exchangeDeclare(EXCHANGE_NAME, "direct");

		String severity = getSeverity(argv);
		String message = getMessage(argv);
		// 第二参数为routeKey，指定消息应当发给那个队列
		channel.basicPublish(EXCHANGE_NAME, severity, null, message.getBytes());
		System.out.println(" [x] Sent '" + severity + "':'" + message + "'");

		channel.close();
		connection.close();
	}

	private static String getSeverity(String[] argv) {

		if (argv.length == 0) {

			return "info";
		}
		String str = argv[0];

		String routeKey = str.replaceAll("=.*", "");

		return routeKey;
	}

	private static String getMessage(String[] argv) {
		if (argv.length == 0) {

			return "mistake happen!!!";
		}
		String str = argv[0];
		String message = str.replaceAll(".*=", "");
		return message;
	}

}