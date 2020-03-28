package com.fightwithoutfire.activemq.demo.queue;

import org.apache.activemq.broker.BrokerService;

/**
 * @author xxx
 * @create 2020-03-26 22:06
 */
public class EmbedBroker {
    public static void main(String[] args) throws Exception {
        BrokerService brokerService = new BrokerService();
        brokerService.setUseJmx(true);
        brokerService.addConnector("tcp://localhost:61616");
        brokerService.start();
    }
}
