package com.aciveMQ;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 在非持久订阅模式中，需要先启动消费者，让后生产者发布消息，才能接收到
 * @author may
 *
 */
public class Consumer {
	private static final String BROKER_URL = "tcp://192.168.243.128:61616";

	public static void main(String[] args) {

		ConnectionFactory connectionFactory = null;// 连接工厂
		Connection connection = null;// 连接
		Session session = null;// 会话
		Topic destination;// 目标
		MessageConsumer messageConsumer;

		try {
			connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
			connection = connectionFactory.createConnection();
			connection.start();
			// 第一个参数表示是否加事物操作，第二个参数Session.AUTO_ACKNOWLEDGE表示自动确认接收，
			// 消费消息不需要加事物
			session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
			destination = session.createTopic("吴沐惠.你默默的真心");// 创建一个叫做myFirstQueue的目标主题
			messageConsumer = session.createConsumer(destination);//这里才会真正的在activeMQ创建队列
			
			//connection.getMetaData().getJMSXPropertyNames();JMX属性
			


			// 阻塞，知道有消息出现
			TextMessage textMessage = (TextMessage) messageConsumer.receive();
			
			if (textMessage != null) {
				
				System.out.println(textMessage.getText());

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
