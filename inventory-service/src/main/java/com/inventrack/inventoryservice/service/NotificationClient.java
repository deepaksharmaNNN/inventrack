package com.inventrack.inventoryservice.service;

import com.inventrack.inventoryservice.dto.LowStockAlert;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class NotificationClient {

    private final RestTemplate restTemplate;

    @Value("${notification.service.url:http://localhost:8084/api/notifications/low-stock}")
    private String notificationUrl;

    public void sendLowStockAlert(LowStockAlert alert){
        try {
            restTemplate.postForLocation(notificationUrl, alert, String.class);
            System.out.println("Low stock alert sent for product Name: " + alert.getProductName());
        }catch (Exception e){
            System.err.println("❌ Failed to send notification: " + e.getMessage());
        }
    }
}
