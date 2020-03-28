package com.fightwithoutfire.activemq.demo.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * @author xxx
 * @create 2020-03-26 20:42
 */
public class JmsConsumer_TX {

    public static final String ACTIVEMQ_URL = "tcp://192.168.11.135:61616";
    public static final String QUEUE_NAME = "queue01";

    public static void main(String[] args) throws JMSException, IOException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(true, Session.CLIENT_ACKNOWLEDGE );
        Queue queue = session.createQueue(QUEUE_NAME);
        MessageConsumer consumer = session.createConsumer(queue);
        while (true) {
            TextMessage message = (TextMessage) consumer.receive(4000L );
            if(message != null){
                System.out.println("message:"+message.getText());


            }else {
                break;
            }


        }
        consumer.close();

        session.commit();
        session.close();
        connection.close();
        System.out.println("END");
    }
}
