package com.eventpulse.EventPulse.application.port;

import com.eventpulse.EventPulse.domain.Event;

public interface EventProducer {
    void produce(Event event);
}
