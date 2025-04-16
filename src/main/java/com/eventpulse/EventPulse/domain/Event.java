package com.eventpulse.EventPulse.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Event {
    private final String userId;
    private final String eventType;
    private final long timestamp;

    @JsonCreator
    public Event(@JsonProperty("userId") String userId,
                 @JsonProperty("eventType") String eventType,
                 @JsonProperty("timestamp") long timestamp) {
        this.userId = userId;
        this.eventType = eventType;
        this.timestamp = timestamp;
    }
}
