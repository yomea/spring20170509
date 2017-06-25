package com.activeMQ.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * 定义监听器
 * @author may
 *
 */
public class MyMessageListener implements MessageListener {

	@Override
	public void onMessage(Message message) {
		
		if(message != null) {
			TextMessage tm = (TextMessage) message;
			String text = null;
			try {
				text = tm.getText();
			} catch (JMSException e) {
				e.printStackTrace();
			}
			System.out.println(text);
		}

	}

}
