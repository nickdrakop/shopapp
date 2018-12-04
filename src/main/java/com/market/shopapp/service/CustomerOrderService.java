/**
 @author nick.drakopoulos
 */

package com.market.shopapp.service;

import com.market.shopapp.dao.CustomerOrderDao;
import com.market.shopapp.domain.CustomerOrderEntity;
import com.market.shopapp.dto.CustomerOrderDto;
import com.market.shopapp.mapper.CustomerOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerOrderService {

    private final CustomerOrderDao customerOrderDao;

    private final CustomerOrderMapper customerOrderMapper;

    @Autowired
    public CustomerOrderService(CustomerOrderDao customerOrderDao, CustomerOrderMapper customerOrderMapper) {
        this.customerOrderDao = customerOrderDao;
        this.customerOrderMapper = customerOrderMapper;
    }

    public Integer create(CustomerOrderDto customerOrderDto) {

        CustomerOrderEntity customerOrderEntity = customerOrderMapper.mapToCustomerOrderEntity(customerOrderDto);
        return customerOrderDao.create(customerOrderEntity);
    }

    public List<CustomerOrderDto> fetchAll(Instant startDate, Instant endDate) {

        List<CustomerOrderEntity> customerOrderEntities = customerOrderDao.findAll(startDate, endDate);
        return customerOrderEntities.stream()
            .map(customerOrderMapper::mapToCustomerOrderDto)
            .collect(Collectors.toList());
    }
}
