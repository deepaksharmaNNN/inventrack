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
        return inventoryRepository.findFirstByProductId(productId)
                .orElseThrow(() -> new RuntimeException("Inventory not found for productId: " + productId));
    }

    public Inventory save(Inventory inv) {
        return inventoryRepository.save(inv);
    }

    public Inventory updateStock(Long id, int quantity) {
        Inventory inv = inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory not found with id: " + id));
        inv.setQuantity(quantity);
        return inventoryRepository.save(inv);
    }

    public void delete(Long id) {
        inventoryRepository.deleteById(id);
    }
}
