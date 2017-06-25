package com.rabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 发送消息后，如果服务器的队列中没有可发现的消费者，那么就会放在队列中等待消费者
 * 可以使用rabbitmqctl.bat list_queues命令查看某个队列的消息个数
 * @author may
 *
 */
public class Send {

	private final static String QUEUE_NAME = "hello";

	public static void main(String[] args) throws Exception {
		//创建连接工厂
		ConnectionFactory factory = new ConnectionFactory();
		//设置连接rabbitMQ服务器的ip
		factory.setHost("localhost");
//		factory.setPort(5672);
		//创建一个连接到服务器的链接
		Connection connection = factory.newConnection();
		//创建连接通道
		Channel channel = connection.createChannel();
		
		/**
		 * 定义一个队列
		 * queueDeclare(String queue,//队列的名字
		 * boolean durable, //定义一个耐用队列，即持久化，如果RabbitMQ服务挂机，重启后还能恢复这个队列。
		 * boolean exclusive, //排他队列，只能在当前链接中可用，如果这个连接关闭，那么也就无效了。
		 * boolean autoDelete,//在连接断开后自动删除这个队列。
		 * Map<String, Object> arguments)
		 */
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		
//		channel.queueDeclareNoWait(queue, durable, exclusive, autoDelete, arguments);
		
		String message = "Hello World!";
		/**
		 * Parameters:
		 *	exchange: the exchange to publish the message to交换机
		 *	routingKey: the routing key 路由key，这里就是队列的名字，表示要发送到这个队列上
		 *	props: other properties for the message - routing headers etc//这个信息的属性，如持久性纯文本(MessageProperties.PERSISTENT_TEXT_PLAIN)
		 *	body: the message body 要发送的信息
		 */
		channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
		
		
		System.out.println(" [x] Sent '" + message + "'");
		
		channel.close();
		
		connection.close();
	}

}
