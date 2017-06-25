package com.rabbitMQ.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class QueueListenter implements MessageListener {

    @Override
    public void onMessage(Message msg) {
        try{
            System.out.println(msg.toString());
            System.out.println(new String(msg.getBody()));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
