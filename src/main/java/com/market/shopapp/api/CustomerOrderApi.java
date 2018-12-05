/**
 @author nick.drakopoulos
 */

package com.market.shopapp.api;

import com.market.shopapp.dto.CustomerOrderDto;
import com.market.shopapp.service.CustomerOrderService;
import com.market.shopapp.util.DateFormatterUtil;
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
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/orders")
public class CustomerOrderApi {
    private static final Logger LOG = LoggerFactory.getLogger(CustomerOrderApi.class);

    private final CustomerOrderService customerOrderService;

    private final DateFormatterUtil dateFormatterUtil;

    @Autowired
    public CustomerOrderApi(CustomerOrderService customerOrderService,
                            DateFormatterUtil dateFormatterUtil) {
        this.customerOrderService = customerOrderService;
        this.dateFormatterUtil = dateFormatterUtil;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Integer create(@RequestBody @Valid CustomerOrderDto customerOrderDto) {

        LOG.info("A new request was received in save with request: {}", customerOrderDto);
        return customerOrderService.save(customerOrderDto);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<CustomerOrderDto> getAll(@RequestParam(value = "start-date", required = false) String startDate,
                                         @RequestParam(value = "end-date", required = false) String endDate) {

        Instant startDateInstant = getInstantFromString(startDate);
        Instant endDateInstant = getInstantFromString(endDate);

        LOG.info("A new request was received in getAll");
        return customerOrderService.fetchAll(startDateInstant, endDateInstant);
    }

    private Instant getInstantFromString(String stringDate) {
        Instant instant = null;
        if (Optional.ofNullable(stringDate).isPresent()) {
            instant = dateFormatterUtil.getInstant(stringDate);
        }
        return instant;
    }
}
