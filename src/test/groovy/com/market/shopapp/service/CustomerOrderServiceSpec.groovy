/**
 @author nick.drakopoulos
 */
package com.market.shopapp.service

import com.market.shopapp.dao.CustomerOrderDao
import com.market.shopapp.dao.ProductDao
import com.market.shopapp.domain.CustomerOrderEntity
import com.market.shopapp.domain.ProductEntity
import com.market.shopapp.dto.CustomerOrderDto
import com.market.shopapp.helper.ProductHelper
import com.market.shopapp.mapper.CustomerOrderMapper
import spock.lang.Specification

import java.time.Instant

class CustomerOrderServiceSpec extends Specification implements ProductHelper {

    CustomerOrderService service

    CustomerOrderDao customerOrderDao
    ProductDao productDao
    CustomerOrderMapper customerOrderMapper

    Instant someInstant

    def setup() {
        someInstant = Instant.now()

        customerOrderDao = Mock(CustomerOrderDao)
        productDao = Mock(ProductDao)
        customerOrderMapper = Mock(CustomerOrderMapper)
        service = new CustomerOrderService(customerOrderDao, productDao, customerOrderMapper)
    }

    def "should successfully call the save method"() {
        given:
            List<Integer> productIds = [1,2]
            List<ProductEntity> productEntities = createSomeProductEntities(someInstant)
            CustomerOrderDto customerOrderDto = new CustomerOrderDto(email: "a@gmail.com", productIds: productIds)
            Integer expectedCustomerOrderId = 5
        when:
            Integer customerOrderId = service.save(customerOrderDto)

        then:
            expectedCustomerOrderId == customerOrderId

        and:
            1 * productDao.findAllById(productIds) >> productEntities
            1 * customerOrderDao.save(_ as CustomerOrderEntity) >> {
                CustomerOrderEntity entity ->
                    assert entity.email == customerOrderDto.email
                    assert entity.products.size() == 2
                    assert entity.totalOrderValue == 3000
                    return expectedCustomerOrderId
            }
            0 * _
    }

    def "should successfully call the fetchAll method"() {
        given:
            List<CustomerOrderEntity> customerOrderEntities = [new CustomerOrderEntity(id: 4), new CustomerOrderEntity(id: 5), new CustomerOrderEntity(id: 6)]
            Instant fromDate = Instant.now().minusSeconds(60)
            Instant toDate = Instant.now()

        when:
            List<CustomerOrderDto> result = service.fetchAll(fromDate, toDate)

        then:
            result.size() == 3
            result.id.contains(4)
            result.id.contains(5)
            result.id.contains(6)

        and:
            1 * customerOrderDao.findAll(fromDate, toDate) >> customerOrderEntities
            3 * customerOrderMapper.mapToCustomerOrderDto(_ as CustomerOrderEntity) >> {
                CustomerOrderEntity entity ->
                    return new CustomerOrderDto(id: entity.id)
            }
            0 * _
    }
}
