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

    public Inventory updateStock(Long id, int quantity){
        Inventory inventory = inventoryRepository.findById(id).orElseThrow(()-> new RuntimeException("Inventory not found"));
        inventory.setQuantity(quantity);
        inventoryRepository.save(inventory);
        // Trigger low stock notification if needed
        if(quantity <= inventory.getThreshold()){
            LowStockAlert alert = LowStockAlert.builder()
                    .productId(inventory.getProductId())
                    .productName(getByProductId(inventory.getProductId()).toString())
                    .currentQuantity(quantity)
                    .threshold(inventory.getThreshold())
                    .email("dsharma2828@gmail.com") // Placeholder email
                    .build();
            notificationClient.sendLowStockAlert(alert);
        }
        return inventory;
    }

}
