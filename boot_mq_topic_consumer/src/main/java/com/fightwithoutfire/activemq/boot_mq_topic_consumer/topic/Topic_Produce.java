package com.fightwithoutfire.activemq.boot_mq_topic_consumer.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Topic;
import java.util.UUID;

/**
 * @author xxx
 * @create 2020-03-27 0:00
 */
@Component
public class Topic_Produce {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Topic topic;

    @Scheduled(fixedDelay = 3000L)
    public void produceTopic(){
        jmsMessagingTemplate.convertAndSend(topic,"*******topic:"+ UUID.randomUUID().toString().substring(0, 6));
    }

}
