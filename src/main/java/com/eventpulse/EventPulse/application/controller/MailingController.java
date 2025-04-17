package com.eventpulse.EventPulse.application.controller;

import com.eventpulse.EventPulse.application.service.MailingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mailing")
@RequiredArgsConstructor
@Slf4j
public class MailingController {

    private final MailingService mailingService;

    @PostMapping("/send-to-all")
    public ResponseEntity<String> sendMessagesToAllUsers() {
        try {
            mailingService.sendMessagesToAllUsers();
            return ResponseEntity.ok("Повідомлення успішно надіслано всім користувачам");
        } catch (Exception e) {
            log.error("❌ Помилка під час надсилання повідомлень користувачам: {}", e.getMessage());
            return ResponseEntity.status(500).body("Не вдалося надіслати повідомлення");
        }
    }
}