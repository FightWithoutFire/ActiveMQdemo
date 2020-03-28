package com.fightwithoutfire.activemq.boot_mq_produce.produce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import java.util.UUID;

/**
 * @author xxx
 * @create 2020-03-26 23:08
 */
@Component
public class Queue_Produce {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;


    public void productMsg(){
        jmsMessagingTemplate.convertAndSend(queue,"****: "+ UUID.randomUUID().toString().substring(0,6));
    }

    @Scheduled(fixedDelay = 3000)
    public void produceMsgScheduled(){
        jmsMessagingTemplate.convertAndSend(queue,"****schedule: "+ UUID.randomUUID().toString().substring(0,6));
        System.out.println("**********produceMsgSend");
    }

}
