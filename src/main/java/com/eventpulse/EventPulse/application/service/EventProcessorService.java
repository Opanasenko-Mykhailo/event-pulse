package com.eventpulse.EventPulse.application.service;

import com.eventpulse.EventPulse.domain.Event;

public interface EventProcessorService {
    void process(Event event);
}
