package com.activeMQ;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:springApplication.xml")
public class ProducerTest {
	
	@Autowired
	private JmsTemplate jmsTemplate = null;
	
	@org.junit.Test
	public void Test() {
		
		//jmsTemplate.send(destination, messageCreator);可以指定目的地
		
		jmsTemplate.send(new MessageCreator() {//使用spring中配置文件的默认的目的地
			
			@Override
			public Message createMessage(Session session) throws JMSException {
				TextMessage tm = session.createTextMessage("hello world");
				return tm;
			}
		});
		
	}
	

}
