/**
 @author nick.drakopoulos
 */

package com.market.shopapp.api;

import com.market.shopapp.dto.CustomerOrderDto;
import com.market.shopapp.service.CustomerOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.Instant;

@RestController
@RequestMapping(path = "/api/order")
public class CustomerOrderApi {
    private static final Logger LOG = LoggerFactory.getLogger(CustomerOrderApi.class);

    private final CustomerOrderService customerOrderService;

    @Autowired
    public CustomerOrderApi(CustomerOrderService customerOrderService) {
        this.customerOrderService = customerOrderService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void create(@RequestBody @Valid CustomerOrderDto customerOrderDto) {

        LOG.info("A new request was received in create with request: {}", customerOrderDto);
        customerOrderService.create(customerOrderDto);
    }

    @RequestMapping(method = RequestMethod.GET)
    public void getAll(@RequestParam("start-date") Instant startDate,
                       @RequestParam("end-date") Instant endDate) {

        LOG.info("A new request was received in getAll");
        customerOrderService.fetchAll(startDate, endDate);
    }

}
