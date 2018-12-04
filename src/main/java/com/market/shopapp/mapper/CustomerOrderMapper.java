/**
 @author nick.drakopoulos
 */

package com.market.shopapp.mapper;

import com.market.shopapp.domain.CustomerOrderEntity;
import com.market.shopapp.domain.ProductEntity;
import com.market.shopapp.dto.CustomerOrderDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerOrderMapper {

    public CustomerOrderDto mapToCustomerOrderDto(CustomerOrderEntity entity) {
        CustomerOrderDto dto = new CustomerOrderDto();

        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setOrderCreatedAt(entity.getCreatedAt());
        dto.setTotalOrderValue(entity.getTotalOrderValue());

        List<Integer> productIds = entity.getProducts().stream()
                .map(ProductEntity::getId)
                .collect(Collectors.toList());
        dto.setProductIds(productIds);

        return dto;
    }

}
