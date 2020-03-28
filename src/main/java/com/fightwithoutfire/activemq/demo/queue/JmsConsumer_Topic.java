package com.fightwithoutfire.activemq.demo.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * @author xxx
 * @create 2020-03-26 16:04
 */
public class JmsConsumer_Topic {
    public static final String ACTIVEMQ_URL = "tcp://192.168.11.135:61616";
    public static final String TOPIC_NAME = "topic1";

    public static void main(String[] args) throws JMSException, IOException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = activeMQConnectionFactory.createConnection();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(TOPIC_NAME);
        MessageConsumer consumer = session.createConsumer(topic);
        connection.start();
//        while (true) {
//            TextMessage message = (TextMessage) consumer.receive();
//            if(message != null){
//                System.out.println("message:"+message.getText());
//            }else {
//                break;
//            }
//
//
//        }

//        consumer.setMessageListener(new MessageListener() {
//            @Override
//            public void onMessage(Message message) {
//                if(message!=null && message instanceof TextMessage){
//                    String text = null;
//                    try {
//                        text = ((TextMessage) message).getText();
//                    } catch (JMSException e) {
//                        e.printStackTrace();
//                    }
//                    System.out.println("message:"+text);
//                }
//            }
//        });
        consumer.setMessageListener(message -> {

            if(message!=null && message instanceof TextMessage){
            String text = null;
            try {
                text = ((TextMessage) message).getText();
            } catch (JMSException e) {
                e.printStackTrace();
            }
            System.out.println("message:"+text);
        }

        });
        System.in.read();
        consumer.close();
        session.close();
        connection.close();
        System.out.println("END");
}
}
