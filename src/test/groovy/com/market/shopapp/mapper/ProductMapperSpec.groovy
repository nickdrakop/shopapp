/**
 @author nick.drakopoulos
 */
package com.market.shopapp.mapper

import com.market.shopapp.domain.ProductEntity
import com.market.shopapp.dto.ProductDto
import spock.lang.Specification

import java.time.Instant

class ProductMapperSpec extends Specification {

    ProductMapper mapper
    Instant someInstant

    def setup() {
        someInstant = Instant.now()
        mapper = new ProductMapper()
    }

    def "should successfully call the mapToProductDto method"() {
        given:

            ProductEntity entity = new ProductEntity(id: 1, name: "product1", price: 1400, quantity: 1, createdAt: someInstant)

        when:
            ProductDto dto = mapper.mapToProductDto(entity)

        then:
            assertProductEntityEqualsDto(entity, dto)
            0 * _
    }

    def "should successfully call the mapToProductEntity method"() {
        given:

            ProductDto dto = new ProductDto(id: 1, name: "product1", price: 1400)

        when:
            ProductEntity entity = mapper.mapToProductEntity(dto)

        then:
            assertProductEntityEqualsDto(entity, dto)
            0 * _
    }

    def "should successfully call the mapToProductEntities method"() {
        given:

            List<ProductDto> dtos = [new ProductDto(id: 1, name: "product1", price: 1400),
                                     new ProductDto(id: 2, name: "product2", price: 1600)]

        when:
            Set<ProductEntity> entities = mapper.mapToProductEntities(dtos)

        then:
            assertProductEntitiesEqualsDtos(entities, dtos)
            0 * _
    }

    def "should successfully call the mapToProductDtos method"() {
        given:

            Set<ProductEntity> entities = [new ProductEntity(id: 1, name: "product1", price: 1400, quantity: 1, createdAt: someInstant),
                                            new ProductEntity(id: 2, name: "product2", price: 1600, quantity: 1, createdAt: someInstant)]

        when:
            Set<ProductDto> dtos = mapper.mapToProductDtos(entities)

        then:
            assertProductEntitiesEqualsDtos(entities, dtos)
            0 * _
    }

    private static assertProductEntitiesEqualsDtos(Collection<ProductEntity> entities, Collection<ProductDto> dtos) {
        dtos.forEach{
            dto ->
                ProductEntity foundEntity = entities.find { entity -> entity.id == dto.id }
                assertProductEntityEqualsDto(foundEntity, dto)
        }
        true
    }

    private static assertProductEntityEqualsDto(ProductEntity entity, ProductDto dto) {
        assert entity.id == dto.id
        assert entity.name == dto.name
        assert entity.price == dto.price
        assert entity.quantity == dto.quantity
        entity
    }
}
