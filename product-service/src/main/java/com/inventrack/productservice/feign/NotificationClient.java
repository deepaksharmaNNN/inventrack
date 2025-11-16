package com.inventrack.productservice.feign;

import com.inventrack.productservice.dto.LowStockAlert;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notification-service", url = "http://localhost:8084")
public interface NotificationClient {
    @PostMapping("/api/notifications/low-stock")
    void sendLowStockAlert(@RequestBody LowStockAlert alert);
}
