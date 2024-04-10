package com.sdub.pbliga.pbligawebscraper.messaging.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    @Value("${messaging.topics.scrapper-data}")
    @Qualifier("scrapper-data-topic-name")
    private String scrapperDataTopicName;

    @Value("${messaging.topics.default}")
    private String defaultTopicName;

    @Qualifier("scrapper-data-topic-name")
    public String getScrapperDataTopicName() {
        return scrapperDataTopicName;
    }

    public String getDefaultTopicName() {
        return defaultTopicName;
    }

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put("bootstrap.servers", bootstrapServers);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic scrapperDataTopic() {
        return TopicBuilder.name(scrapperDataTopicName)
                .partitions(10)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic defaultTopic() {
        return TopicBuilder.name(defaultTopicName)
                .partitions(10)
                .replicas(1)
                .build();
    }
}

