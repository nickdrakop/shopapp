/**
 @author nick.drakopoulos
 */
package com.market.shopapp.mapper

import com.market.shopapp.domain.CustomerOrderEntity
import com.market.shopapp.dto.CustomerOrderDto
import com.market.shopapp.helper.ProductHelper
import spock.lang.Specification

import java.time.Instant

class CustomerOrderMapperSpec extends Specification implements ProductHelper{

    CustomerOrderMapper mapper
    Instant someInstant

    def setup() {
        someInstant = Instant.now()
        mapper = new CustomerOrderMapper()
    }

    def "should successfully call the findAll method"() {
        given:

            CustomerOrderEntity entity = new CustomerOrderEntity(id: 1, email: "a@gmail.com", products:createSomeProductEntities(someInstant), totalOrderValue: 3000, createdAt: someInstant.plusSeconds(5))

        when:
            CustomerOrderDto dto = mapper.mapToCustomerOrderDto(entity)

        then:
            dto.with {
                assert id == entity.id
                assert email == entity.email
                assert productIds == [1,2]
                assert totalOrderValue == entity.totalOrderValue
                assert orderCreatedAt == entity.createdAt
                it
            }
            0 * _
    }
}
