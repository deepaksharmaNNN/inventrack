package com.inventrack.productservice.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class PurchaseResponse {
    String status;
    String message;
    Long productId;
    int quantityPurchased;
}
