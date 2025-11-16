package com.inventrack.productservice.controller;

import com.inventrack.productservice.dto.PurchaseRequest;
import com.inventrack.productservice.dto.PurchaseResponse;
import com.inventrack.productservice.entity.Product;
import com.inventrack.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private  final ProductService productService;

    @GetMapping("/health")
    public String healthCheck() {
        return "Product Service is up and running!";

    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        return productService.updateProduct(id, productDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

//    @PostMapping("/purchase")
//    public Boolean purchaseProduct(@RequestParam Long productId,
//                                   @RequestParam int qty) {
//        return productService.purchase(productId, qty);
//    }
    // POST: http://localhost:8082/api/products/purchase
    @PostMapping("/purchase")
    public PurchaseResponse purchase(@RequestBody PurchaseRequest request) {
        return productService.purchaseProduct(request);
    }

}
