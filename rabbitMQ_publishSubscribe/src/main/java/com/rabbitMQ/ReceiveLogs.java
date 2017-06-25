package com.rabbitMQ;

/**
 * rabbitmqctl add_vhost testmq
 * rabbitmqctl set_permissions -p testmq root .* .* .*
 */
import com.rabbitmq.client.*;

import java.io.IOException;

public class ReceiveLogs {
	//转发器名称
  private static final String EXCHANGE_NAME = "logs";

  public static void main(String[] argv) throws Exception {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");
    factory.setUsername("root");
    factory.setPassword("root");
    factory.setVirtualHost("testmq");
    factory.setPort(5672);
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();
    //可以使用rabbitmqctl list_exchanges查看有哪些转发器，命名为amq.*
    channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
    //定义一个排他的，自动删除的，非持久性的队列，名称是随机的
    String queueName = channel.queueDeclare().getQueue();
    //转发器绑定队列
    //可使用rabbitmqctl list_bindings查看绑定列表
    channel.queueBind(queueName, EXCHANGE_NAME, "");

    System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

    Consumer consumer = new DefaultConsumer(channel) {
      @Override
      public void handleDelivery(String consumerTag, Envelope envelope,
                                 AMQP.BasicProperties properties, byte[] body) throws IOException {
        String message = new String(body, "UTF-8");
        System.out.println(" [x] Received '" + message + "'");
      }
    };
    boolean autoAck = true;
    channel.basicConsume(queueName, autoAck, consumer);
  }
}
