package com.inventrack.inventoryservice.controller;

import com.inventrack.inventoryservice.entity.Inventory;
import com.inventrack.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    // POSTMAN: GET http://localhost:8083/api/inventory/health
    @GetMapping("/health")
    public String health() {
        return "inventory-service: OK";
    }

    // POSTMAN: GET http://localhost:8083/api/inventory
    @GetMapping("/all") // http://localhost:8083/api/inventory/all
    public List<Inventory> getAll() {
        return inventoryService.findAll();
    }

    // POSTMAN: GET http://localhost:8083/api/inventory/by-product/1
    @GetMapping("/by-product/{productId}")
    public Inventory getByProductId(@PathVariable Long productId) {
        return inventoryService.getByProductId(productId);
    }

    // POSTMAN: POST http://localhost:8083/api/inventory
    // BODY:
    // {
    //   "productId": 1,
    //   "quantity": 10,
    //   "threshold": 3
    // }
    @PostMapping
    public Inventory addInventory(@RequestBody Inventory inv) {
        return inventoryService.save(inv);
    }

    // POSTMAN: PUT http://localhost:8083/api/inventory/1?quantity=5
    @PutMapping("/{id}")
    public Inventory updateStock(@PathVariable Long id, @RequestParam int quantity) {
        return inventoryService.updateStock(id, quantity);
    }

    // POSTMAN: DELETE http://localhost:8083/api/inventory/1
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        inventoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
