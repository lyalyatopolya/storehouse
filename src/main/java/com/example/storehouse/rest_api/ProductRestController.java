package com.example.storehouse.rest_api;

import com.example.storehouse.dto.ProductDto;
import com.example.storehouse.repository.ProductRepository;
import com.example.storehouse.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class ProductRestController {

    private final ProductService productService;
    private final ProductRepository productRepository;

    public ProductRestController(ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @PostMapping("/product/create")
    public ResponseEntity<?> createProduct(@RequestBody ProductDto productDto) {
        try {
            ProductDto createdProduct = productService.createProduct(productDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Cannot create product: " + ex.getMessage());
        }
    }

    @PutMapping("/product/update")
    public ResponseEntity<?> updateProduct(@RequestBody ProductDto productDto) {
        try {
            ProductDto updatedProduct = productService.updateProduct(productDto);
            return ResponseEntity.ok(updatedProduct);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Cannot update product: " + ex.getMessage());
        }
    }

    @DeleteMapping("/product/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        try {
            productService.deleteProduct(UUID.fromString(id));
            return ResponseEntity.noContent().build(); // 204

        } catch (Exception e) {
            return ResponseEntity.internalServerError().build(); // 500
        }
    }
}