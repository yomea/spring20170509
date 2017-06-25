package com.aciveMQ;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Producer {
	
	private static final String BROKER_URL = "tcp://localhost:61616";
	
	public static void main(String[] args) {
		ConnectionFactory connectionFactory;// 连接工厂
		Connection connection = null;// 连接
		Session session = null;// 会话
		Queue destination;// 目标
		MessageProducer messageProducer;// 消息生产者

		connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);

		try {
			connection = connectionFactory.createConnection();
			
			connection.start();
			// 第一个参数表示是否加事物操作，第二个参数Session.AUTO_ACKNOWLEDGE表示自动确认接收，
			//
			session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
			destination = session.createQueue("爆炸");// 创建一个叫做myFirstQueue的目标队列
			session.createTemporaryQueue();
			
			
			messageProducer = session.createProducer(destination);// 创建消息生产者
			TextMessage tm = session.createTextMessage("hello world");
			
			
			messageProducer.send(tm);
			
			session.commit();// 启动了事物就必须提交，否则不能发消息
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
