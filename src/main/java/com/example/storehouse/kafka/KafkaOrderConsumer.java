package com.example.storehouse.kafka;

import com.example.storehouse.dto.OrderDto;
import com.example.storehouse.model.Product;
import com.example.storehouse.repository.ProductRepository;
import com.example.storehouse.service.OrderService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class KafkaOrderConsumer {

    private final ProductRepository productRepository;

    private final OrderService orderService;

    public KafkaOrderConsumer(ProductRepository productRepository, OrderService orderService) {
        this.productRepository = productRepository;
        this.orderService = orderService;
    }

    @KafkaListener(topics = "shop-order-requests")
    public void consumeOrder(OrderDto orderDto) {
        List<String> rejectReasons = new ArrayList<>();
        List<Product> productListToSave = new ArrayList<>();
        orderDto.getProductDtoList().forEach(productDto -> {
            Product product = productRepository.findById(productDto.getId()).orElse(null);
            if (Objects.isNull(product)) {
                rejectReasons.add(String.format("Продукт не найден: id=%s, name=%s",
                        productDto.getId(), productDto.getName()));
            }
            if (product.getCount() < productDto.getCount()) {
                rejectReasons.add(String.format("Недостаточное кол-во продукта: %s, текущее кол-во: %s",
                        productDto.getName(), product.getCount()));
            }
            product.setCount(product.getCount() - productDto.getCount());
            productListToSave.add(product);
        });
        orderService.updateProductsAndSentKafkaMessage(rejectReasons, orderDto, productListToSave);
    }
}
