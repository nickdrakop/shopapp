/**
 @author nick.drakopoulos
 */
package com.market.shopapp.dao

import com.market.shopapp.domain.ProductEntity
import com.market.shopapp.repository.ProductRepository
import spock.lang.Specification

class ProductDaoSpec extends Specification {

    ProductDao dao
    ProductRepository productRepository

    def setup() {
        productRepository = Mock(ProductRepository)
        dao = new ProductDao(productRepository)
    }

    def "should successfully call the findAll method"() {
        given:
            List<ProductEntity> expectedEntities = Mock(List)
        when:
            List<ProductEntity> result = dao.findAll()

        then:
            expectedEntities == result

        and:
            1 * productRepository.findAll() >> expectedEntities
            0 * _
    }

    def "should successfully call the findAllById method"() {
        given:
            List<ProductEntity> expectedEntities = Mock(List)
            List<Integer> productIds = Mock(List)
        when:
            List<ProductEntity> result = dao.findAllById(productIds)

        then:
            expectedEntities == result

        and:
            1 * productRepository.findAllById(productIds) >> expectedEntities
            0 * _
    }

    def "should successfully call the save method"() {
        given:
            ProductEntity entityToSave = Mock(ProductEntity)
            Integer expectedSavedProductId = 5

        when:
            Integer saveEntityId = dao.save(entityToSave)

        then:
            saveEntityId == expectedSavedProductId

        and:
            1 * entityToSave.getId() >> expectedSavedProductId
            1 * productRepository.save(entityToSave) >> entityToSave
            0 * _
    }
}
