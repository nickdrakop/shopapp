/**
 @author nick.drakopoulos
 */
package com.market.shopapp.api

import com.market.shopapp.dto.ProductDto
import com.market.shopapp.service.ProductService
import spock.lang.Specification

class ProductApiSpec extends Specification {

    ProductApi api
    ProductService productService

    ProductDto productDto
    Integer expectedId

    def setup() {
        productDto = new ProductDto(name: "product1", price: 1400)
        expectedId = 5

        productService = Mock(ProductService)
        api = new ProductApi(productService)
    }

    def "should successfully call the create api"() {
        when:
            Integer id = api.create(productDto)

        then:
            expectedId == id

        and:
            1 * productService.save(productDto) >> expectedId
            0 * _
    }

    def "should successfully call the update api"() {
        when:
            Integer id = api.update(productDto)

        then:
            expectedId == id

        and:
            1 * productService.save(productDto) >> expectedId
            0 * _
    }

    def "should successfully call the getAll api"() {
        given:
            List<ProductDto> expectedProducts = Mock(List)
        when:
            List<ProductDto> allProducts = api.getAll()

        then:
            expectedProducts == allProducts

        and:
            1 * productService.fetchAll() >> expectedProducts
            0 * _
    }
}
