package com.inventrack.inventoryservice.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Long productId;

    Integer quantity;

    Integer threshold;

    public boolean isLowStock() {
        return quantity != null && threshold != null && quantity <= threshold;
    }
}
