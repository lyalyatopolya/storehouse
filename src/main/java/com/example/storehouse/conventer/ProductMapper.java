package com.example.storehouse.conventer;

import com.example.storehouse.model.dto.ProductDto;
import com.example.storehouse.model.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);


    Product toEntity(ProductDto productDto);

    ProductDto toDto(Product product);

    void updateEntityFromDto(ProductDto productDto, @MappingTarget Product product);

}
