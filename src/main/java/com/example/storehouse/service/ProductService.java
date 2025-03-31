package com.example.storehouse.service;

import com.example.storehouse.conventer.ProductConverter;
import com.example.storehouse.dto.ProductDto;
import com.example.storehouse.model.Product;
import com.example.storehouse.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(ProductConverter::fromProduct)
                .collect(Collectors.toList());
    }

    public ProductDto createProduct(ProductDto productDto) {
        Product product = ProductConverter.fromProductDto(productDto);
        Product saved = productRepository.save(product);
        return ProductConverter.fromProduct(saved);
    }

    public ProductDto updateProduct(ProductDto productDto) {
        Product product = productRepository.findById(productDto.getId()).orElseThrow(() -> new RuntimeException("product not found"));
        ProductConverter.setDataFromProductDto(product, productDto);
        Product saved = productRepository.save(product);
        return ProductConverter.fromProduct(saved);
    }

    public void deleteProduct(UUID productId) {
        productRepository.deleteById(productId);
    }
}