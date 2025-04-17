package com.eventpulse.EventPulse.infrastructure.rabbitmq;

import com.eventpulse.EventPulse.application.port.EventSender;
import com.eventpulse.EventPulse.domain.Event;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class RabbitMQEventProducer implements EventSender {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public void send(Event event) {
        try {
            String json = objectMapper.writeValueAsString(event);
            rabbitTemplate.convertAndSend("event-processing-queue", json);
            log.info("🐰 [RabbitMQ Producer] Подію відправлено в чергу event-processing-queue: {}", event);
        } catch (Exception e) {
            log.error("❌ [RabbitMQ Producer] Помилка при відправленні події в RabbitMQ: {}, {}", event, e.getMessage());
        }
    }
}