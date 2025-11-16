package com.inventrack.productservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "inventory-service",
        url = "http://localhost:8083")
public interface InventoryClient {

    @PutMapping("/api/inventory/reduce")
    Boolean reduceStock(@RequestParam Long productId,@RequestParam Integer quantity);
}
