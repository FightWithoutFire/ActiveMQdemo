package com.fightwithoutfire.activemq.demo.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * @author xxx
 * @create 2020-03-26 19:26
 */
public class JmsConsumer_Topic_Persist {

    public static final String ACTIVEMQ_URL = "tcp://192.168.11.135:61616";
    public static final String TOPIC_NAME = "topic1";

    public static void main(String[] args) throws JMSException, IOException {
        System.out.println("****z4");
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        //activeMQConnectionFactory.setClientID("z4");
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.setClientID("z4");
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Topic topic = session.createTopic(TOPIC_NAME);

        TopicSubscriber durableSubscriber = session.createDurableSubscriber(topic, "remark...");

        connection.start();
        Message message = durableSubscriber.receive();

        while (null != message){
            TextMessage textMessage = (TextMessage) message;
            System.out.println("****收到的信息"+textMessage.getText());
            message = durableSubscriber.receive();
        }


        session.close();
        connection.close();
        System.out.println("END");
    }
}
