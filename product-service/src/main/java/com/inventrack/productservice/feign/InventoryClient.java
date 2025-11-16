package com.inventrack.productservice.feign;

import com.inventrack.productservice.dto.InventoryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "inventory-service",
        url = "http://localhost:8083")
public interface InventoryClient {

    @PutMapping("/api/inventory/reduce")
    Boolean reduceStock(@RequestParam Long productId,@RequestParam Integer quantity);

    @GetMapping("/api/inventory/by-product/{productId}")
    InventoryResponse getByProductId(@PathVariable Long productId);

    @PutMapping("/api/inventory/{id}")
    InventoryResponse updateStock(
            @PathVariable Long id,
            @RequestParam("quantity") Integer quantity
    );
}
