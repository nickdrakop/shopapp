/**
 @author nick.drakopoulos
 */

package com.market.shopapp.mapper;

import com.market.shopapp.domain.ProductEntity;
import com.market.shopapp.dto.ProductDto;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductEntity mapToProductEntity(ProductDto productDto) {
        ProductEntity productEntity = new ProductEntity();

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
}
