package com.inventrack.notificationservice.service;

import com.inventrack.notificationservice.dto.LowStockAlert;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public void sendLowStockAlert(LowStockAlert alert) {
        System.out.println(
                "EMAIL DISABLED — Low stock alert for product: "
                        + alert.getProductName()
                        + ", Qty: " + alert.getCurrentQuantity()
        );
    }
}
