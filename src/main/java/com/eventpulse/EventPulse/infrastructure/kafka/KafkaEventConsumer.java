package com.eventpulse.EventPulse.infrastructure.kafka;

import com.eventpulse.EventPulse.application.port.EventSender;
import com.eventpulse.EventPulse.application.service.EventConsumerService;
import com.eventpulse.EventPulse.domain.Event;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaEventConsumer {

    private final EventConsumerService eventConsumerService;
    private final ObjectMapper objectMapper;
    private final EventSender eventSender;
    private static final long DELAY_MS = 200;

    @KafkaListener(topics = "user-events", groupId = "event-group")
    public void listen(String message) {
        try {
            Event event = objectMapper.readValue(message, Event.class);
            log.info("📥 [Kafka Consumer] Отримано подію з топіка user-events: {}", event);

            eventConsumerService.consume(event);
            log.info("🔄 [Kafka Consumer] Подію передано в EventConsumerService: {}", event);

            eventSender.send(event);
            log.info("📤 [Kafka Consumer] Подію відправлено в RabbitMQ: {}", event);
            Thread.sleep(DELAY_MS);
        } catch (Exception e) {
            log.error("❌ [Kafka Consumer] Помилка при обробці події: {}, {}", message, e.getMessage());
        }
    }
}