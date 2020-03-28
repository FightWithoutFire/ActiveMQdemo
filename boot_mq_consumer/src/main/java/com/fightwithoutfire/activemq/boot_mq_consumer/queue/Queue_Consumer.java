package com.fightwithoutfire.activemq.boot_mq_consumer.queue;

import org.springframework.jms.JmsException;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

/**
 * @author xxx
 * @create 2020-03-26 23:39
 */

@Component
public class Queue_Consumer {
    @JmsListener(destination = "${myqueue}")
    public void receive(TextMessage textMessage) throws JMSException {

        System.out.println("******消费者收到消息："+ textMessage.getText());
    }
}
