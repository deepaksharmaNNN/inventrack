package com.inventrack.notificationservice.controller;

import com.inventrack.notificationservice.dto.LowStockAlert;
import com.inventrack.notificationservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notifications")
public class NotificationController {
    private final EmailService emailService;

    @GetMapping("/health")
    public String healthCheck() {
        return "Notification Service is up and running!";
    }

    @PostMapping("/low-stock")
    public String sendLowStockAlert(@RequestBody LowStockAlert alert) {
        emailService.sendLowStockAlert(alert);
        return "Low stock alert email sent for product ID: " + alert.getProductId();
    }

}
