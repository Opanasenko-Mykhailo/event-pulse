package com.eventpulse.EventPulse.application.service.impl;

import com.eventpulse.EventPulse.application.port.EventProducer;
import com.eventpulse.EventPulse.application.service.EventProducerService;
import com.eventpulse.EventPulse.domain.Event;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventProducerServiceImpl implements EventProducerService {
    private final EventProducer eventProducer;
    private final Random random = new Random();
    private final String[] eventTypes = {"login", "register", "logout"};

    @Override
    @Scheduled(fixedRate = 2000)
    public void generateEvent() {
        Event event = new Event(
                "user-" + random.nextInt(100),
                eventTypes[random.nextInt(eventTypes.length)],
                System.currentTimeMillis()
        );
        eventProducer.produce(event);
        log.info("✅ Створено подію: {}", event);
    }
}
