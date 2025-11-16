package com.inventrack.productservice.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level= AccessLevel.PRIVATE)
public class StockUpdateRequest {
    Long productId;
    int quantity;
}
