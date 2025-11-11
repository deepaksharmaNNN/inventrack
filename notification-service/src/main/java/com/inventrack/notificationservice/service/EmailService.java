package com.inventrack.notificationservice.service;

import com.inventrack.notificationservice.dto.LowStockAlert;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;

    public void sendLowStockAlert(LowStockAlert alert){
        try {
            var message = mailSender.createMimeMessage();
            var helper = new MimeMessageHelper(message);

            helper.setTo(alert.getEmail());
            helper.setSubject("Low Stock Alert: " + alert.getProductName());
            helper.setText(buildEmailBody(alert), true);
            mailSender.send(message);
            System.out.println("Low stock alert email sent to " + alert.getProductId());
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private String buildEmailBody(LowStockAlert alert) {
        return """
            <h2>⚠️ Low Stock Alert</h2>
            <p><strong>Product:</strong> %s</p>
            <p><strong>Current Quantity:</strong> %d</p>
            <p><strong>Threshold:</strong> %d</p>
            <p>Please restock as soon as possible.</p>
            """.formatted(alert.getProductName(), alert.getCurrentQuantity(), alert.getThreshold());
    }

}
