# Event Pulse

Spring Boot program to simulate message distribution using Kafka, RabbitMQ, Redis and H2.

## Features
- Kafka: Events via `user-events` topic
- RabbitMQ: processes `event-processing-queue`
- Redis: event statistics
- H2: user data in memory
- Mailing: notifications at `/api/mailing/send-to-all`
- Event processing delay 0.2s

## Prerequisites
- Java 22, Gradle, Docker, Docker Compose