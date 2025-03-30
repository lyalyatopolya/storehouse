package com.example.storehouse.dto;

import com.example.storehouse.model.Product;

import java.util.UUID;

public class ProductDto {

    public ProductDto() {}

    private UUID id;

    private String name;

    private long count = 0L;

    private String comment;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
