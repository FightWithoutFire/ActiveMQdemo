package com.fightwithoutfire.activemq.demo.queue;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQMessageProducer;
import org.apache.activemq.AsyncCallback;

import javax.jms.*;
import java.util.UUID;

/**
 * @author xxx
 * @create 2020-03-27 22:17
 */
public class JmsProduce_AsyncSend {
    public static final String ACTIVEMQ_URL = "tcp://192.168.11.135:61616";
    public static final String QUEUE_NAME = "queue-Asyncsend";

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        activeMQConnectionFactory.setUseAsyncSend(true);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(QUEUE_NAME);
        ActiveMQMessageProducer activeMQMessageProducer = (ActiveMQMessageProducer) session.createProducer(queue);
        activeMQMessageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);
        TextMessage textMessage = null;
        for(int i = 0; i < 3; i++){
            textMessage = session.createTextMessage("MessageListener---" + i);
            //textMessage.setJMSDeliveryMode(DeliveryMode.NON_PERSISTENT);
            textMessage.setJMSMessageID(UUID.randomUUID().toString()+"---orderFightWithoutFire");
            String msgId = textMessage.getJMSMessageID();
            activeMQMessageProducer.send(textMessage, new AsyncCallback() {
                @Override
                public void onSuccess() {
                    System.out.println(msgId+" has been ok send");
                }

                @Override
                public void onException(JMSException e) {
                    System.out.println(msgId+" fail to send to mq");
                }
            });
        }
        activeMQMessageProducer.close();
        session.close();
        connection.close();
        System.out.println("END");
    }
}
