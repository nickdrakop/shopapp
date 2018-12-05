/**
 @author nick.drakopoulos
 */
package com.market.shopapp.api

import com.market.shopapp.dto.CustomerOrderDto
import com.market.shopapp.service.CustomerOrderService
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

import java.time.Instant

class CustomerOrderApiSpec extends Specification {

    CustomerOrderApi api
    CustomerOrderService customerOrderService

    CustomerOrderDto customerOrderDto
    Integer expectedId

    @Shared
    Instant someInstant

    def setup() {
        customerOrderDto = new CustomerOrderDto(email: "a@gmail.com", productIds: [1, 2])
        expectedId = 5
        someInstant = Instant.now()

        customerOrderService = Mock(CustomerOrderService)
        api = new CustomerOrderApi(customerOrderService)
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

    @Unroll
    def "should successfully call the getAll api when startDate:#startDate & endDate:#endDate"() {
        given:
            List<CustomerOrderDto> expectedCustomerOrders = Mock(List)
        when:
            List<CustomerOrderDto> allCustomerOrders = api.getAll(startDate, endDate)

        then:
            expectedCustomerOrders == allCustomerOrders

        and:
            1 * customerOrderService.fetchAll(startDate, endDate) >> expectedCustomerOrders
            0 * _
        where:
            startDate | endDate
            null      | null
            null      | someInstant
            someInstant | null
    }
}
