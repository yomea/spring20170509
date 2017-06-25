package com.activeMQ;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:springApplication.xml")
public class ConsumerTest {
	
	@Autowired
	private JmsTemplate jmsTemplate = null;
	
	@org.junit.Test
	public void Test() {
		
		String message = (String) jmsTemplate.receiveAndConvert();
		System.out.println(message);
		
	}
	

}
