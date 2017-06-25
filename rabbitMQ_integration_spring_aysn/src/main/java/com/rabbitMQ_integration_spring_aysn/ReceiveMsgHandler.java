package com.rabbitMQ_integration_spring_aysn;

public class ReceiveMsgHandler {

	public void handleMessage(String text) {
		System.out.println("Received: " + text);
	}
}

