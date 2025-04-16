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
        logStats(event);
    }

    private void logStats(Event event) {
        log.info("📝 Оброблено подію: {}", event);

        for (String type : new String[]{"login", "register", "logout"}) {
            String count = redisStatsService.getEventCount(type);
            log.info("📊 Статистика - Події типу '{}': {}", type, count != null ? count : "0");
        }
    }
}
