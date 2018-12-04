/**
 @author nick.drakopoulos
 */

package com.market.shopapp.mapper;

import com.market.shopapp.domain.CustomerOrderEntity;
import com.market.shopapp.dto.CustomerOrderDto;
import org.springframework.stereotype.Component;

@Component
public class CustomerOrderMapper {

    public CustomerOrderEntity mapToCustomerOrderEntity(CustomerOrderDto customerOrderDto) {
        CustomerOrderEntity customerOrderEntity = new CustomerOrderEntity();

        customerOrderEntity.setEmail(customerOrderDto.getEmail());
        customerOrderEntity.setProducts(customerOrderDto.getProducts());

        return customerOrderEntity;
    }

    public CustomerOrderDto mapToCustomerOrderDto(CustomerOrderEntity entity) {
        CustomerOrderDto dto = new CustomerOrderDto();

        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setOrderCreatedAt(entity.getCreatedAt());
        dto.setTotalOrderValue(entity.getTotalOrderValue());
        dto.setProducts(entity.getProducts());

        return dto;
    }
}
