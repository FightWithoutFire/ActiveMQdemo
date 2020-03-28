package com.fightwithoutfire.activemq.boot_mq_topic_produce.topic;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

/**
 * @author xxx
 * @create 2020-03-27 0:05
 */
@Component
public class Topic_Consumer {

    @JmsListener(destination = "${myTopic}")
    public void receive(TextMessage textMessage)  {
        try {
            System.out.println("*******topic_listen: "+textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
