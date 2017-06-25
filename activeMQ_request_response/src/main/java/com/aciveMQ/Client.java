package com.aciveMQ;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TemporaryQueue;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Client {
	
	private ConnectionFactory cf = null;
	private Connection connection = null;
	private Session session = null;
	private Destination destination = null;
	private TemporaryQueue tq = null;
	private  MessageConsumer responseConsumer = null;
	
	public Client() {
		
		try {
			cf = new ActiveMQConnectionFactory("tcp://192.168.243.128:61616");
			
			connection = cf.createConnection();
			connection.start();
			//开启事物，并且会话自动ack
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
	}
	
	public void sendRequest() {
		try {
			TextMessage message = session.createTextMessage("hello");
			destination = session.createQueue("request_queue");
			
			Destination tempDest = session.createTemporaryQueue();

			responseConsumer = session.createConsumer(tempDest);
			
			MessageProducer mp = session.createProducer(destination);
			
			message.setJMSReplyTo(tq);
			
			mp.send(message);
	//		session.commit();
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void recieve() {
		
		try {
			
			TextMessage message = (TextMessage) responseConsumer.receive();
			if(message != null) {
				
				System.out.println(message.getText());
				this.close();
			} 
			
		} catch (JMSException e) {
			e.printStackTrace();
		} 
		
		
		
	}
	
	public void close() {
		
		try {
			session.close();
			connection.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void main(String[] args) {
		
		Client client = new Client();
		
		client.sendRequest();
		
		client.recieve();
		
		
	}
	
	

}
