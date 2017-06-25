package com.rabbitMQ;

import java.io.IOException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

/**
 * 启动多个worker，那么发送的消息会被平均分配到这些worker上，按他们启动注册到服务器的顺序
 * @author may
 *
 */
public class Worker {

	private final static String QUEUE_NAME = "hello";

	public static void main(String[] argv) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		/**
		 * queue the name of the queue durable true if we are declaring a
		 * durable queue (the queue will survive a server restart) exclusive
		 * true if we are declaring an exclusive queue (restricted to this
		 * connection) autoDelete true if we are declaring an autodelete queue
		 * (server will delete it when no longer in use) arguments other
		 * properties (construction arguments) for the queue
		 */
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
		// 定义一个消费者
		final Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body) throws IOException {
				String message = new String(body, "UTF-8");

				System.out.println(" [x] Received '" + message + "'");
				try {
					doWork(message);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					System.out.println(" [x] Done");
				}
			}
		};
		// 异步
		/**
		 * queue the name of the queue 队列名 autoAck true if the server should
		 * consider messages acknowledged once delivered; false if the server
		 * should expect explicit acknowledgements callback an interface to the
		 * consumer object
		 * 可以通过以下命令去查看队列中没有返回ack的消息个数
		 * rabbitmqctl list_queues name messages_ready messages_unacknowledged
		 */
		boolean autoAck = true;
		channel.basicConsume(QUEUE_NAME, autoAck, consumer);

		// rabbitmqctl.bat list_queues 可以列出当前有多少个队列
	}

	private static void doWork(String task) throws InterruptedException {
		for (char ch : task.toCharArray()) {
			if (ch == '.')
				Thread.sleep(1000);
		}
	}

}
