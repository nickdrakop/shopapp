/**
 @author nick.drakopoulos
 */
package com.market.shopapp.mapper

import com.market.shopapp.domain.CustomerOrderEntity
import com.market.shopapp.dto.CustomerOrderDto
import com.market.shopapp.helper.ProductHelper
import com.market.shopapp.util.DateFormatterUtil
import spock.lang.Specification

import java.time.Instant

class CustomerOrderMapperSpec extends Specification implements ProductHelper{

    CustomerOrderMapper mapper
    DateFormatterUtil dateFormatterUtil
    Instant someInstant

    def setup() {
        someInstant = Instant.now()
        dateFormatterUtil = Mock(DateFormatterUtil)
        mapper = new CustomerOrderMapper(dateFormatterUtil)
    }

    def "should successfully call the findAll method"() {
        given:
            CustomerOrderEntity entity = new CustomerOrderEntity(
                    id: 1,
                    email: "a@gmail.com",
                    products:createSomeProductEntities(someInstant),
                    totalOrderValue: 3000,
                    createdAt: someInstant.plusSeconds(5)
            )
            String formattedDate = "formatted date"

        when:
            CustomerOrderDto dto = mapper.mapToCustomerOrderDto(entity)

        then:
            dto.with {
                assert id == entity.id
                assert email == entity.email
                assert productIds == [1,2]
                assert totalOrderValue == entity.totalOrderValue
                assert orderCreatedAt == formattedDate
                it
            }
            1 * dateFormatterUtil.instantToStringTimezonedDate(entity.createdAt) >> formattedDate
            0 * _
    }
}
