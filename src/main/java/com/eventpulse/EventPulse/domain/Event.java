package com.eventpulse.EventPulse.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Getter
public class Event {
    private final String userId;
    private final String eventType;
    private final long timestamp;
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                    .withZone(ZoneId.systemDefault());

    @JsonCreator
    public Event(@JsonProperty("userId") String userId,
                 @JsonProperty("eventType") String eventType,
                 @JsonProperty("timestamp") long timestamp) {
        this.userId = userId;
        this.eventType = eventType;
        this.timestamp = timestamp;
    }

    public String getFormattedTimestamp() {
        return FORMATTER.format(Instant.ofEpochMilli(timestamp));
    }

    @Override
    public String toString() {
        return "Event{" +
                "userId='" + userId + '\'' +
                ", eventType='" + eventType + '\'' +
                ", timestamp=" + getFormattedTimestamp() +
                '}';
    }
}