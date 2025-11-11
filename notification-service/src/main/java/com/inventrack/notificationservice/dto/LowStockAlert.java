package com.inventrack.notificationservice.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LowStockAlert {
    Long productId;
    String productName;
    int currentQuantity;
    int threshold;
    String email; // Recipient email address
}
