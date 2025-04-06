package com.example.storehouse.kafka;

import com.example.storehouse.dto.OrderDto;
import com.example.storehouse.dto.ProductDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.nio.charset.StandardCharsets.UTF_8;

public class OrderDtoDeserializer implements Deserializer<OrderDto> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public OrderDto deserialize(String topic, byte[] data) {
        try {
            String json = new String(data, UTF_8);
            JsonNode rootNode = objectMapper.readTree(json);
            OrderDto orderDto = new OrderDto();
            orderDto.setId(UUID.fromString(rootNode.get("id").asText()));
            if (rootNode.has("productDtoList")) {
                JsonNode productsNode = rootNode.get("productDtoList");
                List<ProductDto> products = new ArrayList<>();
                for (JsonNode productNode : productsNode) {
                    ProductDto product = new ProductDto();
                    product.setId(UUID.fromString(productNode.get("id").asText()));
                    product.setName(productNode.get("name").asText());
                    product.setCount(productNode.get("count").asInt());
                    products.add(product);
                }
                orderDto.setProductDtoList(products);
            } else {
                throw new IllegalArgumentException("productList is empty");
            }
            return orderDto;
        } catch (Exception e) {
            throw new SerializationException("Failed to deserialize OrderDto", e);
        }
    }
}