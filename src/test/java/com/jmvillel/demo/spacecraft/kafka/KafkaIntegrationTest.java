package com.jmvillel.demo.spacecraft.kafka;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import com.jmvillel.demo.spacecraft.kafka.consumer.SpaceCraftsConsumer;
import com.jmvillel.demo.spacecraft.kafka.producer.SpaceCraftsProducer;

@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
class KafkaIntegrationTest {

    @Autowired
    private SpaceCraftsConsumer consumer;

    @Autowired
    private SpaceCraftsProducer producer;

    @Value("${kafka.topic}")
    private String topic;

    @Test
    @DisplayName("Consumer receive message from topic after Producer send the message")
    public void whenSendingWithProducer_then_ConsumerReceiveMessage() 
      throws Exception {
        String data = "X-Wing taking off now";
        
        producer.send(topic, data);
        
        boolean messageConsumed = consumer.getLatch().await(10, TimeUnit.SECONDS);
        assertTrue(messageConsumed);
        assertTrue(consumer.getPayload()!=null && consumer.getPayload().contains(data));
    }
}
