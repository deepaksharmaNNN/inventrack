package com.inventrack.inventoryservice.service;

import com.inventrack.inventoryservice.dto.LowStockAlert;
import com.inventrack.inventoryservice.entity.Inventory;
import com.inventrack.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final NotificationClient notificationClient;

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
                .orElseThrow(() -> new RuntimeException("Inventory not found"));

        inv.setQuantity(quantity);
        Inventory updated = inventoryRepository.save(inv);

        // Low stock alert
        if (quantity <= inv.getThreshold()) {
            LowStockAlert alert = new LowStockAlert();
            alert.setProductId(inv.getProductId());
            alert.setProductName("Product-" + inv.getProductId());
            alert.setCurrentQuantity(inv.getQuantity());
            alert.setThreshold(inv.getThreshold());
            alert.setEmail("deepak@example.com");

            notificationClient.sendLowStockAlert(alert);
        }

        return updated;
    }

    public Boolean reduceStock(Long productId, int quantity) {
        Inventory inv = getByProductId(productId);

        if (inv.getQuantity() < quantity) {
            return false;  // not enough stock
        }

        inv.setQuantity(inv.getQuantity() - quantity);

        // Save updated
        inventoryRepository.save(inv);

        // Low stock alert check
        if (inv.getQuantity() <= inv.getThreshold()) {
            LowStockAlert alert = new LowStockAlert();
            alert.setProductId(productId);
            alert.setProductName("Product-" + productId);
            alert.setCurrentQuantity(inv.getQuantity());
            alert.setThreshold(inv.getThreshold());
            alert.setEmail("deepak@example.com");

            notificationClient.sendLowStockAlert(alert);
        }

        return true;
    }


    public void delete(Long id) {
        inventoryRepository.deleteById(id);
    }
}
