/**
 @author nick.drakopoulos
 */
package com.market.shopapp.dao

import com.market.shopapp.domain.CustomerOrderEntity
import com.market.shopapp.repository.CustomerOrderRepository
import spock.lang.Specification

import java.time.Instant

class CustomerOrderDaoSpec extends Specification {

    CustomerOrderDao dao
    CustomerOrderRepository customerOrderRepository

    def setup() {
        customerOrderRepository = Mock(CustomerOrderRepository)
        dao = new CustomerOrderDao(customerOrderRepository)
    }

    def "should successfully call the findAll method"() {
        given:
            List<CustomerOrderEntity> expectedEntities = Mock(List)
            Instant startDate = Instant.now().minusSeconds(60)
            Instant endDate = Instant.now()

        when:
            List<CustomerOrderEntity> result = dao.findAll(startDate, endDate)

        then:
            expectedEntities == result

        and:
            1 * customerOrderRepository.findAll(startDate, endDate) >> expectedEntities
            0 * _
    }

    def "should successfully call the save method"() {
        given:
            CustomerOrderEntity entityToSave = Mock(CustomerOrderEntity)
            Integer expectedSavedCustomerOrderId = 5

        when:
            Integer saveEntityId = dao.save(entityToSave)

        then:
            saveEntityId == expectedSavedCustomerOrderId

        and:
            1 * entityToSave.getId() >> expectedSavedCustomerOrderId
            1 * customerOrderRepository.save(entityToSave) >> entityToSave
            0 * _
    }
}
