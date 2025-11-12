package com.inventrack.inventoryservice.controller;

import com.inventrack.inventoryservice.entity.Inventory;
import com.inventrack.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping("/health")
    public String health() {
        return "Inventory Service is up and running!";
    }

    @GetMapping
    public List<Inventory> getAll(){
        return inventoryService.findAll();
    }

    @GetMapping("/{productId}")
    public Inventory getByProductId(@PathVariable Long productId){
        return inventoryService.getByProductId(productId);
    }

    @PostMapping
    public Inventory addInventory(@RequestBody Inventory inv) {
        return inventoryService.save(inv);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Inventory inv = inventoryService.getByProductId(id);
        inventoryService.delete(inv);
    }

    @PutMapping("/{id}/stock")
    public Inventory updateStock(@PathVariable Long id, @RequestParam int quantity){
        return inventoryService.updateStock(id, quantity);
    }
}
