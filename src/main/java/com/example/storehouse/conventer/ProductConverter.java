package com.example.storehouse.conventer;

import com.example.storehouse.dto.ProductDto;
import com.example.storehouse.model.Product;

public class ProductConverter {

    public static Product fromProductDto(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setComment(productDto.getComment());
        product.setCount(productDto.getCount());
        return product;
    }

    public static ProductDto fromProduct(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setComment(product.getComment());
        productDto.setCount(product.getCount());
        return productDto;
    }

    public static void setDataFromProductDto(Product product, ProductDto productDto) {
        product.setName(productDto.getName());
        product.setComment(productDto.getComment());
        product.setCount(productDto.getCount());
    }
}
