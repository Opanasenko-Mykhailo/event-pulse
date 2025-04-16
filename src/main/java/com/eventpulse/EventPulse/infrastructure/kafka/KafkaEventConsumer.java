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

    @KafkaListener(topics = "user-events", groupId = "event-group")
    public void listen(String message) {
        try {
            log.info("📥 Отримано повідомлення з Kafka: {}", message);
            Event event = objectMapper.readValue(message, Event.class);
            log.info("✅ Подія успішно перетворена: {}", event);
            eventConsumerService.consume(event);
            log.info("📝 Оброблено подію в Kafka Consumer: {}", event);
            eventSender.send(event);
            log.info("📤 Подія відправлена до RabbitMQ: {}", event);
        } catch (Exception e) {
            log.error("❌ Помилка при обробці події: {}", message, e);
        }
    }
}
