package com.fightwithoutfire.activemq.demo.queue;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;

import javax.jms.*;

/**
 * @author xxx
 * @create 2020-03-27 23:24
 */
public class JmsProduce_Redelivery {

    public static final String ACTIVEMQ_URL = "tcp://192.168.11.135:61616";
    public static final String QUEUE_NAME = "Transport";

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);

        /**
         * set RedeliveryPolicy
         */
        RedeliveryPolicy redeliveryPolicy = new RedeliveryPolicy();
        redeliveryPolicy.setMaximumRedeliveries(3);
        activeMQConnectionFactory.setRedeliveryPolicy(redeliveryPolicy);

        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(QUEUE_NAME);
        MessageProducer producer = session.createProducer(queue);
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        for(int i = 0; i < 3; i++){
            TextMessage textMessage = session.createTextMessage("MessageListener---" + i);
            //textMessage.setJMSDeliveryMode(DeliveryMode.NON_PERSISTENT);
            textMessage.setJMSDeliveryMode(DeliveryMode.PERSISTENT);
            producer.send(textMessage);
        }
        producer.close();
        session.close();
        connection.close();
        System.out.println("END");
    }

}
