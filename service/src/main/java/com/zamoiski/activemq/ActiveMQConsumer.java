package com.zamoiski.activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQConsumer {

    @JmsListener(destination = "changeTitle")
    public void processMessages(String message){
        System.out.println(message);
    }
}
