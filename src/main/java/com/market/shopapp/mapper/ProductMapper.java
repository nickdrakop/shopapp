/**
 @author nick.drakopoulos
 */

package com.market.shopapp.mapper;

import com.market.shopapp.domain.ProductEntity;
import com.market.shopapp.dto.ProductDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    public ProductEntity mapToProductEntity(ProductDto productDto) {
        ProductEntity productEntity = new ProductEntity();

        productEntity.setId(productDto.getId());
        productEntity.setName(productDto.getName());
        productEntity.setQuantity(productDto.getQuantity());
        productEntity.setPrice(productDto.getPrice());

        return productEntity;
    }

    public ProductDto mapToProductDto(ProductEntity entity) {
        ProductDto dto = new ProductDto();

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setQuantity(entity.getQuantity());
        dto.setPrice(entity.getPrice());

        return dto;
    }

    public Set<ProductEntity> mapToProductEntities(List<ProductDto> productDtoList) {
        if(productDtoList == null) {
            return null;
        }

        return productDtoList.stream()
                .map(this::mapToProductEntity)
                .collect(Collectors.toSet());
    }

    public List<ProductDto> mapToProductDtos(Set<ProductEntity> productEntityList) {
        if(productEntityList == null) {
            return null;
        }

        return productEntityList.stream()
                .map(this::mapToProductDto)
                .collect(Collectors.toList());
    }
}
