package com.inventrack.productservice.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level= AccessLevel.PRIVATE)
@AllArgsConstructor
public class LowStockAlert {
    Long productId;
    String productName;
    int currentQuantity;
    int threshold;
    String email;
}
