package com.example.storehouse.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

@Data
public class ProductDto {
    @Schema(description = "Уникальный идентификатор товара UUID", example = "842431d3-34ef-4204-aef6-3a301d0f15f6")
    private UUID id;
    @Schema(description = "Название товара", example = "Мандарин")
    private String name;
    @Schema(description = "Кол-во товара на складе", example = "10")
    private int count = 0;
    @Schema(description = "Комментарий", example = "Упаковка по 500г")
    private String comment;
}
