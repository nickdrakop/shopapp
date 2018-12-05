/**
 @author nick.drakopoulos
 */
package com.market.shopapp.service

import com.market.shopapp.dao.ProductDao
import com.market.shopapp.domain.ProductEntity
import com.market.shopapp.dto.ProductDto
import com.market.shopapp.mapper.ProductMapper
import com.market.shopapp.repository.ProductRepository
import spock.lang.Specification

class ProductServiceSpec extends Specification {

    ProductService service

    ProductDao productDao
    ProductMapper productMapper

    def setup() {
        productDao = Mock(ProductDao)
        productMapper = Mock(ProductMapper)
        service = new ProductService(productDao, productMapper)
    }

    def "should successfully call the save method"() {
        given:
            ProductDto productDto = Mock(ProductDto)
            ProductEntity productEntity = Mock(ProductEntity)
            Integer expectedProductId = 5
        when:
            Integer productId = service.save(productDto)

        then:
            expectedProductId == productId

        and:
            1 * productMapper.mapToProductEntity(productDto) >> productEntity
            1 * productDao.save(productEntity) >> expectedProductId
            0 * _
    }

    def "should successfully call the fetchAll method"() {
        given:
            List<ProductEntity> productEntities = [new ProductEntity(id: 4), new ProductEntity(id: 5), new ProductEntity(id: 6)]

        when:
            List<ProductDto> result = service.fetchAll()

        then:
            result.size() == 3
            result.id.contains(4)
            result.id.contains(5)
            result.id.contains(6)

        and:
            1 * productDao.findAll() >> productEntities
            3 * productMapper.mapToProductDto(_ as ProductEntity) >> {
                ProductEntity entity ->
                    return new ProductDto(id: entity.id)
            }
            0 * _
    }
}
