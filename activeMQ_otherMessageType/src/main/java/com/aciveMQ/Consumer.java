package com.aciveMQ;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * @author may
 *
 */
public class Consumer {
	private static final String BROKER_URL = "tcp://192.168.243.128:61616";

	public static void main(String[] args) {

		ConnectionFactory connectionFactory = null;// 连接工厂
		Connection connection = null;// 连接
		Session session = null;// 会话
		Queue destination;// 目标
		MessageConsumer messageConsumer;

		try {
			connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
			connection = connectionFactory.createConnection();
			// 第一个参数表示是否加事物操作，第二个参数Session.AUTO_ACKNOWLEDGE表示自动确认接收，
			// 消费消息不需要加事物
			session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
			destination = session.createQueue("myFirstQueue");// 创建一个叫做myFirstQueue的目标主题
			messageConsumer = session.createConsumer(destination);
			
			//connection.getMetaData().getJMSXPropertyNames();JMX属性
			

			connection.start();

			// receive（long argue）在取完队列中的所有消息后，会按每1s钟的时间进行读取
			MapMessage message = (MapMessage) messageConsumer.receive(1000);
			
			if (message != null) {
				
				System.out.println(message.getString("歌手") + "演唱" + message.getString("歌唱二小放牛郎"));
				System.out.println(message.getStringProperty("customProperties"));

			}
			
		//	textMessage.acknowledge();//确认消息
			
			// System.out.println(textMessage);

		} catch (JMSException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					session.close();
					connection.close();
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}

		}

	}

}
