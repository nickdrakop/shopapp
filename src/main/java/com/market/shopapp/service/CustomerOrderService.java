/**
 @author nick.drakopoulos
 */

package com.market.shopapp.service;

import com.market.shopapp.dao.CustomerOrderDao;
import com.market.shopapp.dao.ProductDao;
import com.market.shopapp.domain.CustomerOrderEntity;
import com.market.shopapp.domain.ProductEntity;
import com.market.shopapp.dto.CustomerOrderDto;
import com.market.shopapp.exception.AppError;
import com.market.shopapp.exception.ApplicationException;
import com.market.shopapp.mapper.CustomerOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CustomerOrderService {

    private final CustomerOrderDao customerOrderDao;

    private final ProductDao productDao;

    private final CustomerOrderMapper customerOrderMapper;

    @Autowired
    public CustomerOrderService(CustomerOrderDao customerOrderDao,
                                ProductDao productDao,
                                CustomerOrderMapper customerOrderMapper) {
        this.customerOrderDao = customerOrderDao;
        this.productDao = productDao;
        this.customerOrderMapper = customerOrderMapper;
    }

    @Transactional
    public Integer save(CustomerOrderDto customerOrderDto) {
        Set<ProductEntity> productEntities = checkProductsExistenceAndExtract(customerOrderDto.getProductIds());

        CustomerOrderEntity customerOrderEntity = new CustomerOrderEntity();
        customerOrderEntity.setEmail(customerOrderDto.getEmail());
        customerOrderEntity.setProducts(productEntities);
        customerOrderEntity.setTotalOrderValue(calculateTotalPrice(productEntities));

        return customerOrderDao.save(customerOrderEntity);
    }

    public List<CustomerOrderDto> fetchAll(Instant startDate, Instant endDate) {

        List<CustomerOrderEntity> customerOrderEntities = customerOrderDao.findAll(startDate, endDate);
        return customerOrderEntities.stream()
            .map(customerOrderMapper::mapToCustomerOrderDto)
            .collect(Collectors.toList());
    }

    private Set<ProductEntity> checkProductsExistenceAndExtract(List<Integer> productIds) {
        Set<ProductEntity> productEntities = new HashSet<>(productDao.findAllById(productIds));

        if (productEntities.size() != productIds.size()) {
            throw new ApplicationException(AppError.ERROR_FINDING_PRODUCTS);
        }
        return productEntities;
    }

    private Integer calculateTotalPrice(Set<ProductEntity> productEntities) {
        return productEntities.stream()
                .map(ProductEntity::getPrice)
                .mapToInt(Integer::intValue)
                .sum();
    }
}
