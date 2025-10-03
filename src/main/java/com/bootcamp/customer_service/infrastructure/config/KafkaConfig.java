package com.bootcamp.customer_service.infrastructure.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic customerCreatedTopic() {
        return new NewTopic("customer-created", 1, (short) 1);
    }

    @Bean
    public NewTopic customerUpdatedTopic() {
        return new NewTopic("customer-updated", 1, (short) 1);
    }
}
