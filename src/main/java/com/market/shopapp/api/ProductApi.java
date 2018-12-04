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

@RestController
@RequestMapping(path = "/api/product")
public class ProductApi {
    private static final Logger LOG = LoggerFactory.getLogger(ProductApi.class);

    private final ProductService productService;

    @Autowired
    public ProductApi(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void create(@RequestBody @Valid ProductDto productDto) {

        LOG.info("A new request was received in create with request: {}", productDto);
        productService.createOrUpdate(productDto);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void update(@RequestBody @Valid ProductDto productDto) {

        LOG.info("A new request was received in update with request: {}", productDto);
        productService.createOrUpdate(productDto);
    }

    @RequestMapping(method = RequestMethod.GET)
    public void getAll() {

        LOG.info("A new request was received in getAll");
        productService.fetchAll();
    }

}
