package com.eventpulse.EventPulse.application.service.impl;

import com.eventpulse.EventPulse.application.service.EventProcessorService;
import com.eventpulse.EventPulse.domain.Event;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventProcessorServiceImpl implements EventProcessorService {
    @Override
    public void process(Event event) {
        log.info("✅ Подія успішно оброблена: {}", event);
    }
}
