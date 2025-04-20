package com.example.storehouse.service;

import com.example.storehouse.conventer.ProductMapper;
import com.example.storehouse.model.dto.ProductDto;
import com.example.storehouse.model.entity.Product;
import com.example.storehouse.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    public ProductDto createProduct(ProductDto productDto) {
        Product product = productMapper.toEntity(productDto);
        Product saved = productRepository.save(product);
        return productMapper.toDto(saved);
    }

    public ProductDto updateProduct(ProductDto productDto) {
        Product product = productRepository.findById(productDto.getId()).orElseThrow(() -> new RuntimeException("product not found"));
        productMapper.updateEntityFromDto(productDto, product);
        Product saved = productRepository.save(product);
        return productMapper.toDto(saved);
    }

    public void deleteProduct(UUID productId) {
        productRepository.deleteById(productId);
    }
}