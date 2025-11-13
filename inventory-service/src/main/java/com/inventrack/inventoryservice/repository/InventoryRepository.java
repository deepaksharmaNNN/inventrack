package com.inventrack.inventoryservice.repository;

import com.inventrack.inventoryservice.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory,Long> {
    List<Inventory> findByProductId(Long productId);
    Optional<Inventory> findFirstByProductId(Long productId);


}
