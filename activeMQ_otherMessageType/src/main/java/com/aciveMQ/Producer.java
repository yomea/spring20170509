package com.aciveMQ;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Producer {
	
	private static final String BROKER_URL = "tcp://192.168.243.128:61616";
	
	public static void main(String[] args) {
		ConnectionFactory connectionFactory;// 连接工厂
		Connection connection = null;// 连接
		Session session = null;// 会话
		Queue destination;// 目标
		MessageProducer messageProducer;// 消息生产者

		connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);

		try {
			connection = connectionFactory.createConnection();
			
			// 第一个参数表示是否加事物操作，第二个参数Session.AUTO_ACKNOWLEDGE表示自动确认接收，
			//
			session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
			destination = session.createQueue("myFirstQueue");// 创建一个叫做myFirstQueue的目标队列
			
			messageProducer = session.createProducer(destination);// 创建消息生产者

			MapMessage message = session.createMapMessage();
			
			message.setString("歌唱二小放牛郎", "牛儿还在山上吃草，放牛的却不知哪里去了");
			
			message.setString("歌手", "杨慧妍");
			
			message.setStringProperty("customProperties", "test");
			
			connection.start();
			
			messageProducer.send(message);
			
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
