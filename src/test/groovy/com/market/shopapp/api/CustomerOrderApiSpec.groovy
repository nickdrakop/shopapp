/**
 @author nick.drakopoulos
 */
package com.market.shopapp.api

import com.market.shopapp.dto.CustomerOrderDto
import com.market.shopapp.service.CustomerOrderService
import com.market.shopapp.util.DateFormatterUtil
import spock.lang.Specification

import java.time.Instant

class CustomerOrderApiSpec extends Specification {

    CustomerOrderApi api
    CustomerOrderService customerOrderService
    DateFormatterUtil dateFormatterUtil

    CustomerOrderDto customerOrderDto
    Integer expectedId
    List<CustomerOrderDto> expectedCustomerOrders

    Instant someInstant
    Instant someInstant2

    def setup() {
        expectedCustomerOrders = Mock(List)
        customerOrderDto = new CustomerOrderDto(email: "a@gmail.com", productIds: [1, 2])
        expectedId = 5
        someInstant = Instant.now()
        someInstant2 = Instant.now().plusSeconds(30)

        customerOrderService = Mock(CustomerOrderService)
        dateFormatterUtil = Mock(DateFormatterUtil)
        api = new CustomerOrderApi(customerOrderService, dateFormatterUtil)
    }

    def "should successfully call the create api"() {
        when:
            Integer id = api.create(customerOrderDto)

        then:
            expectedId == id

        and:
            1 * customerOrderService.save(customerOrderDto) >> expectedId
            0 * _
    }

    def "should successfully call the getAll api when startDate & endDate are null"() {
        when:
            List<CustomerOrderDto> allCustomerOrders = api.getAll(null, null)

        then:
            expectedCustomerOrders == allCustomerOrders

        and:
            1 * customerOrderService.fetchAll(null, null) >> expectedCustomerOrders
            0 * _
    }

    def "should successfully call the getAll api when startDate is null"() {
        when:
            List<CustomerOrderDto> allCustomerOrders = api.getAll(null, "some formatted date")

        then:
            expectedCustomerOrders == allCustomerOrders

        and:
            1 * dateFormatterUtil.getInstant("some formatted date") >> someInstant
            1 * customerOrderService.fetchAll(null, someInstant) >> expectedCustomerOrders
            0 * _
    }

    def "should successfully call the getAll api when endDate is null"() {
        when:
            List<CustomerOrderDto> allCustomerOrders = api.getAll("some formatted date", null)

        then:
            expectedCustomerOrders == allCustomerOrders

        and:
            1 * dateFormatterUtil.getInstant("some formatted date") >> someInstant
            1 * customerOrderService.fetchAll(someInstant, null) >> expectedCustomerOrders
            0 * _
    }

    def "should successfully call the getAll api when startDate & endDate are given"() {
        when:
            List<CustomerOrderDto> allCustomerOrders = api.getAll("some formatted date1", "some formatted date2")

        then:
            expectedCustomerOrders == allCustomerOrders

        and:
            1 * dateFormatterUtil.getInstant("some formatted date1") >> someInstant
            1 * dateFormatterUtil.getInstant("some formatted date2") >> someInstant2
            1 * customerOrderService.fetchAll(someInstant, someInstant2) >> expectedCustomerOrders
            0 * _
    }
}
