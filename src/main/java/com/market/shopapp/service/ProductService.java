/**
 @author nick.drakopoulos
 */

package com.market.shopapp.service;

import com.market.shopapp.dao.ProductDao;
import com.market.shopapp.domain.ProductEntity;
import com.market.shopapp.dto.ProductDto;
import com.market.shopapp.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductService {

    private final ProductDao productDao;

    private final ProductMapper productMapper;

    @Autowired
    public ProductService(ProductDao productDao, ProductMapper productMapper) {
        this.productDao = productDao;
        this.productMapper = productMapper;
    }

    public Integer createOrUpdate(ProductDto productDto) {

        ProductEntity productEntity = productMapper.mapToProductEntity(productDto);
        return productDao.createOrUpdate(productEntity);
    }

    public List<ProductDto> fetchAll() {

        List<ProductEntity> productEntities = productDao.findAll();
        return productEntities.stream()
            .map(productMapper::mapToProductDto)
            .collect(Collectors.toList());
    }
}
