/**
 @author nick.drakopoulos
 */

package com.market.shopapp.api;

import com.market.shopapp.dto.ProductDto;
import com.market.shopapp.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/products")
public class ProductApi {
    private static final Logger LOG = LoggerFactory.getLogger(ProductApi.class);

    private final ProductService productService;

    @Autowired
    public ProductApi(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Integer create(@RequestBody @Valid ProductDto productDto) {

        LOG.info("A new request was received in save with request: {}", productDto);
        return productService.save(productDto);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Integer update(@RequestBody @Valid ProductDto productDto) {

        LOG.info("A new request was received in update with request: {}", productDto);
        return productService.save(productDto);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<ProductDto> getAll() {

        LOG.info("A new request was received in getAll");
        return productService.fetchAll();
    }

}
