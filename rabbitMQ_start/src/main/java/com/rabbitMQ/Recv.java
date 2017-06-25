package com.rabbitMQ;

import java.io.IOException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class Recv {
	//队列名要和发送者定义的队列一样的名字，它会告诉rabbitMQ它要再哪个队列中获取消息
	private final static String QUEUE_NAME = "hello";

	public static void main(String[] argv) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		/**
		 * queue the name of the queue
			durable true if we are declaring a durable queue (the queue will survive a server restart)
			exclusive true if we are declaring an exclusive queue (restricted to this connection)
			autoDelete true if we are declaring an autodelete queue (server will delete it when no longer in use)
			arguments other properties (construction arguments) for the queue
		 */
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
		//定义一个消费者
		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body) throws IOException {
				String message = new String(body, "UTF-8");
				System.out.println(" [x] Received '" + message + "'");
			}
		};
		//异步
		/**
		 * queue the name of the queue 队列名
		 * 如果autoAck设置为true，这个Consumer在收到消息之后会马上返回ack。服务器将立即删除在内存中排队的消息
		 * false 在消息的任务处理完之后手动返回ack，如果正在处理的时候，消费者发生异常，就不能返回ack，那么就不会删除这个消息，等待发现其他消费者发送给他们
			autoAck true if the server should consider messages acknowledged once delivered; false if the server should expect explicit acknowledgements
			callback an interface to the consumer object
		 */
		channel.basicConsume(QUEUE_NAME, true, consumer);
		
		//rabbitmqctl.bat list_queues 命令可以列出当前有多少个队列
	}
}
