package com.aciveMQ;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicSubscriber;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 持久化主题，订阅，在topic发布后如果客户端不在线，可以进行持久化，等到客户端上线后会继续向客户端发送信息，这个和费持久化的topic不同
 * 非持久化的主题，发布后，如果没有在线的客户端就会丢失信息，再次上线后也不能继续接受消息，因为这个消息已经丢失了
 * @author may
 *
 */
public class Consumer {
	private static final String USERNAME = ActiveMQConnectionFactory.DEFAULT_USER;
	private static final String PASSWORD = ActiveMQConnectionFactory.DEFAULT_PASSWORD;
	private static final String BROKER_URL = ActiveMQConnectionFactory.DEFAULT_BROKER_URL;
	
	
public static void main(String[] args) {
		
		ConnectionFactory connectionFactory = null;// 连接工厂
		Connection connection = null;// 连接
		Session session;// 会话
		Topic destination;// 目标
		
		try {
			connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKER_URL);
			connection = connectionFactory.createConnection();
			//在接收消息前需要先启动这个类进行注册
			connection.setClientID("testID");
			// 第一个参数表示是否加事物操作，第二个参数Session.AUTO_ACKNOWLEDGE表示自动确认接收，
			//消费消息不需要加事物
			session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
			destination = session.createTopic("myFirstQueue");// 创建一个叫做myFirstQueue的目标主题
			//messageConsumer = session.createConsumer(destination);
			TopicSubscriber ts = session.createDurableSubscriber(destination, "t1");
			
			connection.start();
			
			while(true) {
				//receive（long argue）在取完队列中的所有消息后，会按每1s钟的时间进行读取
				TextMessage textMessage = (TextMessage) ts.receive(1000);
				if(textMessage != null) {
					
					System.out.println(textMessage.getText());
					
				}
				System.out.println(textMessage);
			}
		} catch (JMSException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}

		}
		
	}
	
	
}
