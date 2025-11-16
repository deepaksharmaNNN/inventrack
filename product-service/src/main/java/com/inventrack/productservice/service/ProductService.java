package com.inventrack.productservice.service;

import com.inventrack.productservice.dto.InventoryResponse;
import com.inventrack.productservice.dto.LowStockAlert;
import com.inventrack.productservice.dto.PurchaseRequest;
import com.inventrack.productservice.dto.PurchaseResponse;
import com.inventrack.productservice.entity.Product;
import com.inventrack.productservice.feign.InventoryClient;
import com.inventrack.productservice.feign.NotificationClient;
import com.inventrack.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private  final ProductRepository productRepository;
    private final InventoryClient inventoryClient;
    private final NotificationClient notificationClient;

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product productDetails) {
        Product product = getProductById(id);
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        product.setQuantity(productDetails.getQuantity());
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Boolean purchase(Long productId, int qty) {
        Boolean success = inventoryClient.reduceStock(productId, qty);

        if (!success) {
            throw new RuntimeException("Not enough stock!");
        }

        return true;
    }
    public PurchaseResponse purchaseProduct(PurchaseRequest purchaseRequest){
        Product product = getProductById(purchaseRequest.getProductId());

        // step 1: get inventory details
        InventoryResponse inventory = inventoryClient.getByProductId(purchaseRequest.getProductId());

        if(inventory.getQuantity() < purchaseRequest.getQuantity()){
            return new PurchaseResponse(
                    "Failed",
                    "Insufficient stock for product: " + product.getName(),
                    product.getId(),
                    0
            );
        }

        // step 2: decrease stock by calling inventory service
        InventoryResponse updatedInventory = inventoryClient.updateStock(
                inventory.getId(),
                inventory.getQuantity() - purchaseRequest.getQuantity()
        );

        // step 3: Check for low stock -> notify
        if(updatedInventory.getQuantity() <= updatedInventory.getThreshold()){
            LowStockAlert alert = new LowStockAlert(
                    product.getId(),
                    product.getName(),
                    updatedInventory.getQuantity(),
                    updatedInventory.getThreshold(),
                    "dsharma2828@gmail.com"
            );
            notificationClient.sendLowStockAlert(alert);
        }
        // step 4: return response
        return new PurchaseResponse(
                "Success",
                "Purchased " + purchaseRequest.getQuantity() + " units of " + product.getName(),
                product.getId(),
                purchaseRequest.getQuantity()
        );
    }

}
