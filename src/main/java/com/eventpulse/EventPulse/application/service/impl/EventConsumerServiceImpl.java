package com.eventpulse.EventPulse.application.service.impl;

import com.eventpulse.EventPulse.application.service.EventConsumerService;
import com.eventpulse.EventPulse.application.service.EventProcessorService;
import com.eventpulse.EventPulse.domain.Event;
import com.eventpulse.EventPulse.infrastructure.redis.RedisStatsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventConsumerServiceImpl implements EventConsumerService {

    private final EventProcessorService eventProcessor;
    private final RedisStatsService redisStatsService;

    @Override
    public void consume(Event event) {
        redisStatsService.incrementEventCount(event.getEventType());
        eventProcessor.process(event);
        if ("notification".equals(event.getEventType())) {
            log.info("📧 Імітація надсилання сповіщення користувачу [{}]: Отримано нове сповіщення", event.getUserId());
        }
    }
}