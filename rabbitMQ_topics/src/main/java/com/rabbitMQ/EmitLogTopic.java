package com.rabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class EmitLogTopic {

    private static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] argv)
                  throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "topic");

        String routingKey = getRouting(argv);
        String message = getMessage(argv);

        channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes());
        System.out.println(" [x] Sent '" + routingKey + "':'" + message + "'");

        connection.close();
    }
   
    
    private static String getRouting(String[] argv) {

		if (argv.length == 0) {

			return "jr;;p.critical";
		}
		String str = argv[0];

		String routeKey = str.replaceAll("=.*", "");

		return routeKey;
	}

	private static String getMessage(String[] argv) {
		if (argv.length == 0) {

			return "I like play game.";
		}
		String str = argv[0];
		String message = str.replaceAll(".*=", "");
		return message;
	}

    
}