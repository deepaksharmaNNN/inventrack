package com.inventrack.inventoryservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
        name = "notification-service",
        url = "http://localhost:8084"
)
public interface NotificationClient {

    @PostMapping("/api/notifications/low-stock")
    void sendLowStockAlert(Object alert);
}
