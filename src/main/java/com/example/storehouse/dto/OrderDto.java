package com.example.storehouse.dto;

import com.example.storehouse.model.enums.OrderStatus;

import java.util.List;
import java.util.UUID;

public class OrderDto {

    private UUID id;

    private List<ProductDto> productDtoList;

    private String statusComment;

    private OrderStatus orderStatus = OrderStatus.CANCELED;

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getStatusComment() {
        return statusComment;
    }

    public void setStatusComment(String statusComment) {
        this.statusComment = statusComment;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<ProductDto> getProductDtoList() {
        return productDtoList;
    }

    public void setProductDtoList(List<ProductDto> productDtoList) {
        this.productDtoList = productDtoList;
    }
}
