package com.inventrack.inventoryservice.repository;

import com.inventrack.inventoryservice.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory,Long> {
    Inventory findByProductId(Long productId);
}
