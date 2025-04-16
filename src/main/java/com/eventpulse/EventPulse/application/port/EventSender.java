package com.eventpulse.EventPulse.application.port;

import com.eventpulse.EventPulse.domain.Event;

public interface EventSender {
    void send(Event event);
}
