package com.fightwithoutfire.activemq.demo.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author xxx
 * @create 2020-03-26 19:26
 */
public class JmsProduce_Topic_Persist {
    public static final String ACTIVEMQ_URL = "tcp://192.168.11.135:61616";
    public static final String TOPIC_NAME = "topic1";

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = activeMQConnectionFactory.createConnection();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(TOPIC_NAME);

        MessageProducer producer = session.createProducer(topic);
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        connection.start();
        for(int i = 0; i < 3; i++){
            TextMessage textMessage = session.createTextMessage("msg-persist---" + i);
            producer.send(textMessage);
        }
        producer.close();
        session.close();
        connection.close();
        System.out.println("END");
    }
}
