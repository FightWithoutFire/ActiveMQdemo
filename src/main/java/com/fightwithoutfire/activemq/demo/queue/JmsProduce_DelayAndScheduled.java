package com.fightwithoutfire.activemq.demo.queue;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ScheduledMessage;

import javax.jms.*;

/**
 * @author xxx
 * @create 2020-03-27 22:38
 */
public class JmsProduce_DelayAndScheduled {
    public static final String ACTIVEMQ_URL = "tcp://192.168.11.135:61616";
    public static final String QUEUE_NAME = "Transport";

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(QUEUE_NAME);
        MessageProducer producer = session.createProducer(queue);
        long delay = 3 * 1000;
        long period = 4 * 1000;
        int repeat = 5;
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        for(int i = 0; i < 3; i++){
            TextMessage textMessage = session.createTextMessage("MessageListener---" + i);
            //textMessage.setJMSDeliveryMode(DeliveryMode.NON_PERSISTENT);
            textMessage.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, delay);
            textMessage.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_PERIOD, period);
            textMessage.setIntProperty(ScheduledMessage.AMQ_SCHEDULED_REPEAT, repeat);

            producer.send(textMessage);
        }
        producer.close();
        session.close();
        connection.close();
        System.out.println("END");
    }
}
