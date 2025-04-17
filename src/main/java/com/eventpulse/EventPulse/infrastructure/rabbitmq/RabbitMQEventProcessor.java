package com.eventpulse.EventPulse.infrastructure.rabbitmq;

import com.eventpulse.EventPulse.application.service.EventProcessorService;
import com.eventpulse.EventPulse.domain.Event;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class RabbitMQEventProcessor {
    private final EventProcessorService eventProcessorService;
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = "event-processing-queue")
    public void process(String message) {
        try {
            Event event = objectMapper.readValue(message, Event.class);
            log.info("📬 [RabbitMQ Processor] Отримано подію з черги event-processing-queue: {}", event);
            eventProcessorService.process(event);
            log.info("✅ [RabbitMQ Processor] Подію успішно оброблено EventProcessorService: {}", event);
            log.info("_________________________________________________________________________" +
                    "__________________________________________________________________________");
        } catch (Exception e) {
            log.error("❌ [RabbitMQ Processor] Помилка при обробці події: {}, {}", message, e.getMessage());
        }
    }
}