package com.aciveMQ;

import org.apache.activemq.broker.BrokerService;

/**
 * activeMQ的嵌入式的服务器，类似springboot内部嵌入了一个tomcat一样
 * 这样就不需要安装activeMQ了
 * @author may
 *
 */
public class Broker_method_1 {
	
	public static void main(String[] args) throws Exception {
		
		BrokerService bs = new BrokerService();
		
		bs.setUseJmx(true);
		
		bs.addConnector("tcp://localhost:61616");
		
		bs.start();
		
	}

}
