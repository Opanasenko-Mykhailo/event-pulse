package com.eventpulse.EventPulse.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Bean
    public Queue eventProcessingQueue() {
        return new Queue("event-processing-queue", false);
    }
}