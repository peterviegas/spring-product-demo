package com.viegas.spring_product_demo.mapper;

import com.viegas.spring_product_demo.dto.ProductDTO;
import com.viegas.spring_product_demo.entity.ProductEntity;

import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {

    public static ProductEntity toEntity (ProductDTO productDTO){
        return new ProductEntity(
            productDTO.getId(),
                productDTO.getName(),
                productDTO.getDescription(),
                productDTO.getPrice(),
                productDTO.isActive()
        );
    }

    public static ProductDTO toDTO (ProductEntity productEntity){
        return new ProductDTO(
                productEntity.getId(),
                productEntity.getName(),
                productEntity.getDescription(),
                productEntity.getPrice(),
                productEntity.isActive()
        );
    }

    public static List<ProductDTO> toDTO (List<ProductEntity> productEntities){
        return productEntities.stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }
}
