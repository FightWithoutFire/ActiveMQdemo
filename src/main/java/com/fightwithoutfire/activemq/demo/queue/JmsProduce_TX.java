package com.fightwithoutfire.activemq.demo.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author xxx
 * @create 2020-03-26 20:40
 */
public class JmsProduce_TX {
    public static final String ACTIVEMQ_URL = "tcp://192.168.11.135:61616";
    public static final String QUEUE_NAME = "queue01";

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(QUEUE_NAME);
        MessageProducer producer = session.createProducer(queue);
        for(int i = 0; i < 3; i++){
            TextMessage textMessage = session.createTextMessage("MessageListener---" + i);
            producer.send(textMessage);
        }

        producer.close();
        session.commit();
        session.close();
        connection.close();
        System.out.println("END");
    }
}
