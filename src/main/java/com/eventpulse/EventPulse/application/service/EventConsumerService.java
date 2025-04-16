package com.eventpulse.EventPulse.application.service;

import com.eventpulse.EventPulse.domain.Event;

public interface EventConsumerService {
    void consume(Event event);
}
