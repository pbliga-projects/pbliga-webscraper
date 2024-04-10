package com.sdub.pbliga.pbligawebscraper;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class PbligaWebscraperApplication {

    public static void main(String[] args) {
        SpringApplication.run(PbligaWebscraperApplication.class, args);
    }
}
