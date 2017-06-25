package com.aciveMQ;

import java.net.URI;

import org.apache.activemq.broker.BrokerFactory;
import org.apache.activemq.broker.BrokerService;

/**
 * activeMQ的嵌入式的服务器，类似springboot内部嵌入了一个tomcat一样
 * 这样就不需要安装activeMQ了
 * @author may
 *
 */
public class Broker_method_2 {
	
	public static void main(String[] args) throws Exception {
		
		String url = "properties:broker.properties";
		
		BrokerService  bs = BrokerFactory.createBroker(new URI(url));
		
		bs.addConnector("tcp://localhost:61616");
		
		bs.start();
		
	}

}
