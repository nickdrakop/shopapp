/**
 @author nick.drakopoulos
 */

package com.market.shopapp.dao;

import com.market.shopapp.domain.CustomerOrderEntity;
import com.market.shopapp.repository.CustomerOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Component
public class CustomerOrderDao {

    private CustomerOrderRepository repository;

    @Autowired
    public CustomerOrderDao(CustomerOrderRepository repository) {
        this.repository = repository;
    }

    public List<CustomerOrderEntity> findAll(Instant startDate, Instant endDate) {
        return repository.findAll(startDate, endDate);
    }

    public Integer create(CustomerOrderEntity entity) {
        return Optional.ofNullable(repository.save(entity))
            .map(CustomerOrderEntity::getId)
            .orElse(null);
    }

}
