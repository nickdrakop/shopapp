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
import java.util.List;

@RestController
@RequestMapping(path = "/api/orders")
public class CustomerOrderApi {
    private static final Logger LOG = LoggerFactory.getLogger(CustomerOrderApi.class);

    private final CustomerOrderService customerOrderService;

    @Autowired
    public CustomerOrderApi(CustomerOrderService customerOrderService) {
        this.customerOrderService = customerOrderService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Integer create(@RequestBody @Valid CustomerOrderDto customerOrderDto) {

        LOG.info("A new request was received in create with request: {}", customerOrderDto);
        return customerOrderService.create(customerOrderDto);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<CustomerOrderDto> getAll(@RequestParam(value = "start-date", required = false) Instant startDate,
                                         @RequestParam(value = "end-date", required = false) Instant endDate) {

        LOG.info("A new request was received in getAll");
        return customerOrderService.fetchAll(startDate, endDate);
    }

}
