package com.eventpulse.EventPulse.infrastructure.redis;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RedisStatsService {
    private final StringRedisTemplate redisTemplate;

    public RedisStatsService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void incrementEventCount(String eventType) {
        String key = "event:count:" + eventType;
        Long newCount = redisTemplate.opsForValue().increment(key);
        log.info("🔼 Лічильник події '{}' збільшено на 1. Нове значення: {}", eventType, newCount);
    }

    public String getEventCount(String eventType) {
        String key = "event:count:" + eventType;
        String count = redisTemplate.opsForValue().get(key);
        log.info("📊 Поточний лічильник події '{}': {}", eventType, count);
        return count;
    }
}
