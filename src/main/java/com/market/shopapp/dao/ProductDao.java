/**
 @author nick.drakopoulos
 */

package com.market.shopapp.dao;

import com.market.shopapp.domain.ProductEntity;
import com.market.shopapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductDao {

    private ProductRepository repository;

    @Autowired
    public ProductDao(ProductRepository repository) {
        this.repository = repository;
    }

    public List<ProductEntity> findAll() {
        return repository.findAll();
    }

    public Integer createOrUpdate(ProductEntity entity) {
        return Optional.ofNullable(repository.save(entity))
            .map(ProductEntity::getId)
            .orElse(null);
    }

}
