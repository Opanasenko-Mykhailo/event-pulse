package com.eventpulse.EventPulse.application.service.impl;

import com.eventpulse.EventPulse.application.port.EventProducer;
import com.eventpulse.EventPulse.application.service.MailingService;
import com.eventpulse.EventPulse.domain.Event;
import com.eventpulse.EventPulse.domain.User;
import com.eventpulse.EventPulse.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailingServiceImpl implements MailingService {

    private final UserRepository userRepository;
    private final EventProducer eventProducer;

    @Override
    public void sendMessagesToAllUsers() {
        Iterable<User> users = userRepository.findAll();
        for (User user : users) {
            Event event = new Event(
                    user.getUsername(),
                    "notification",
                    System.currentTimeMillis()
            );
            eventProducer.produce(event);
            log.info("📧 Створено подію сповіщення для користувача [{}]: {}", user.getUsername(), event);
        }
    }
}