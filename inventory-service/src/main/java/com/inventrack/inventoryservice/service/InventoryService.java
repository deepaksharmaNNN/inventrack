package com.inventrack.inventoryservice.service;

import com.inventrack.inventoryservice.entity.Inventory;
import com.inventrack.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public List<Inventory> findAll() {
        return inventoryRepository.findAll();
    }
    public Inventory getByProductId(Long productId) {
        return inventoryRepository.findByProductId(productId);
    }

    public Inventory save(Inventory inventory) {
        if(inventory.isLowStock()){
            // TODO : Notify when stock is low (Well, later!)
            System.out.println("Warning: Low stock for product ID " + inventory.getProductId());
        }
        return inventoryRepository.save(inventory);
    }

    public void  delete(Inventory inventory) {
        inventoryRepository.delete(inventory);
    }

}
