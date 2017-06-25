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

public class Server {
	
	private ConnectionFactory cf = null;
	private Connection connection = null;
	private Session session = null;
	private Destination destination = null;
	private TemporaryQueue tq = null;
	
	public Server() {
		
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
	
	public void sendResponse() {
		try {
			TextMessage message = session.createTextMessage("world");
			
			MessageProducer mp = session.createProducer(tq);
			
			mp.send(message);
		//	session.commit();
		} catch (JMSException e) {
			e.printStackTrace();
		} finally {
			
			
			this.close();
		}
		
		
	}
	
	public void revieveRequest() {
		
		try {
			destination = session.createQueue("request_queue");
			MessageConsumer mc = session.createConsumer(destination);
			
			TextMessage message = (TextMessage) mc.receive();
			
			tq = (TemporaryQueue) message.getJMSReplyTo();
			
			if(message != null) {
				
				System.out.println(message.getText());
				
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
		
		Server server = new Server();
		
		server.revieveRequest();
		
		server.sendResponse();
		
	}
	
	

}
