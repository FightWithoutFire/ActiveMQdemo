package com.fightwithoutfire.activemq.boot_mq_topic_consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BootMqTopicConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootMqTopicConsumerApplication.class, args);
    }

}
