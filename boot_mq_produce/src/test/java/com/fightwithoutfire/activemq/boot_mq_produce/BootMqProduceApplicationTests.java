package com.fightwithoutfire.activemq.boot_mq_produce;

import com.fightwithoutfire.activemq.boot_mq_produce.produce.Queue_Produce;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

@SpringBootTest(classes = BootMqProduceApplication.class)
@WebAppConfiguration
public class BootMqProduceApplicationTests {

    @Resource
    private Queue_Produce queue_produce;

    @Test
    public void contextLoads() {
        queue_produce.productMsg();
    }

}
