package com.example.storehouse.service;

import com.example.storehouse.dto.OrderDto;
import com.example.storehouse.kafka.KafkaOrderProducerService;
import com.example.storehouse.model.Product;
import com.example.storehouse.model.enums.OrderStatus;
import com.example.storehouse.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    private final KafkaOrderProducerService kafkaOrderProducerService;

    private final ProductRepository productRepository;

    public OrderService(KafkaOrderProducerService kafkaOrderProducerService,
                        ProductRepository productRepository) {
        this.kafkaOrderProducerService = kafkaOrderProducerService;
        this.productRepository = productRepository;
    }

    @Transactional
    public void updateProductsAndSentKafkaMessage(List<String> rejectReasons, OrderDto orderDto, List<Product> productListToSave) {
        if (rejectReasons.isEmpty()) {
            productRepository.saveAll(productListToSave);
            orderDto.setOrderStatus(OrderStatus.FORMED);
            orderDto.setStatusComment("Заказ успешно оформлен");
        } else {
            orderDto.setOrderStatus(OrderStatus.CANCELED);
            orderDto.setStatusComment(String.join(",", rejectReasons));
        }
        kafkaOrderProducerService.sendMessage("store-house-order-requests", orderDto);
    }
}
