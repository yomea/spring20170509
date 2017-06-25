package com.aciveMQ;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Producer {
	
	private static final String USERNAME = ActiveMQConnectionFactory.DEFAULT_USER;
	private static final String PASSWORD = ActiveMQConnectionFactory.DEFAULT_PASSWORD;
	private static final String BROKER_URL = ActiveMQConnectionFactory.DEFAULT_BROKER_URL;
	
	public static void main(String[] args) throws Exception {
		ConnectionFactory connectionFactory;// 连接工厂
		Connection connection = null;// 连接
		Session session;// 会话
		Destination destination;// 目标
		MessageProducer messageProducer;// 消息生产者

		connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKER_URL);

		try {
			connection = connectionFactory.createConnection();
			
			// 第一个参数表示是否加事物操作，第二个参数Session.AUTO_ACKNOWLEDGE表示自动确认接收，
			//
			session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
			destination = session.createTopic("myFirstQueue");// 创建一个叫做myFirstQueue的目标主题
			
			messageProducer = session.createProducer(destination);// 创建消息生产者
			TextMessage tm = session.createTextMessage("hello world");
			//表示发送的是持久化消息
			messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);
			
			connection.start();
			
			messageProducer.send(tm);
			session.commit();// 启动了事物就必须提交，否则不能发消息
		} catch (JMSException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.close();
			}

		}
	}
	

}
