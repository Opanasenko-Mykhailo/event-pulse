package com.eventpulse.EventPulse.infrastructure.kafka;

import com.eventpulse.EventPulse.application.port.EventProducer;
import com.eventpulse.EventPulse.domain.Event;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaEventProducer implements EventProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public void produce(Event event) {
        try {
            String eventJson = objectMapper.writeValueAsString(event);
            kafkaTemplate.send("user-events", event.getUserId(), eventJson);
            log.info("🚀 [Kafka Producer] Подію відправлено в топік user-events: {}", event);
        } catch (Exception e) {
            log.error("❌ [Kafka Producer] Помилка при відправленні події в Kafka: {}, {}", event, e.getMessage());
        }
    }
}