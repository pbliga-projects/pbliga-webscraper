package com.sdub.pbliga.pbligawebscraper.messaging.service;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

public class KafkaMessagingService implements MessagingService {
    //private final KafkaProducer<String, String> kafkaProducer;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    private final String topicName;

    @Autowired
    public KafkaMessagingService(@Value("${messaging.topics.default}") String topicName) {

        this.topicName = topicName;
    }

    @Override
    public void sendMessage(String message) {
        try {
            kafkaTemplate.send(new ProducerRecord<>(topicName, message));
            System.out.println("Message sent successfully to topic: " + topicName);
        } catch (Exception e) {
            System.err.println("Failed to send message to Kafka topic: " + topicName);
            e.printStackTrace();
        }
    }
}