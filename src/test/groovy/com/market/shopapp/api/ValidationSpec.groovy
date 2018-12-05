/**
 @author nick.drakopoulos
 */
package com.market.shopapp.api

import com.market.shopapp.dto.CustomerOrderDto
import com.market.shopapp.dto.ProductDto
import com.market.shopapp.service.ProductService
import spock.lang.Specification
import spock.lang.Unroll

import javax.validation.ConstraintViolation
import javax.validation.Validation
import javax.validation.Validator
import javax.validation.ValidatorFactory

class ValidationSpec extends Specification {

    private Validator validator

    ProductApi api
    ProductService productService

    ProductDto productDto
    Integer expectedId

    def setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory()
        validator = factory.getValidator()

        productDto = new ProductDto(name: "product1", price: 1400)
        expectedId = 5

        productService = Mock(ProductService)
        api = new ProductApi(productService)
    }

    def "successfully validated productDto"() {
        given:
            ProductDto productDto = new ProductDto(name: "someName", price: 1)

        when:
            Set<ConstraintViolation<ProductDto>> violations = validator.validate(productDto);

        then:
            violations.size() == 0
    }

    @Unroll
    def "validation error for productDto due to #scenario"() {
        given:
            ProductDto productDto = new ProductDto(name: givenName, price: givenPrice)

        when:
            Set<ConstraintViolation<ProductDto>> violations = validator.validate(productDto);

        then:
            violations.size() == 1
            violations[0].propertyPath.toString() == expectedFieldWithIssue
            violations[0].message == expectedError

        where:
            givenName | givenPrice | expectedFieldWithIssue | expectedError       | scenario
            ""        | 1200       | "name"                 | "must not be empty" | "name is empty"
            null      | 1200       | "name"                 | "must not be empty" | "name is null"
            "name"    | null       | "price"                | "must not be null"  | "price is null"
    }

    def "successfully validated CustomerOrderDto"() {
        given:
            CustomerOrderDto customerOrderDto = new CustomerOrderDto(email: "a@gmail.com", productIds: [1, 2])

        when:
            Set<ConstraintViolation<CustomerOrderDto>> violations = validator.validate(customerOrderDto);

        then:
            violations.size() == 0
    }

    @Unroll
    def "validation error for CustomerOrderDto due to #scenario"() {
        given:
            CustomerOrderDto customerOrderDto = new CustomerOrderDto(email: givenEmail, productIds: givenIds)

        when:
            Set<ConstraintViolation<CustomerOrderDto>> violations = validator.validate(customerOrderDto);

        then:
            violations.size() == 1
            violations[0].propertyPath.toString() == expectedFieldWithIssue
            violations[0].message == expectedError

        where:
            givenEmail    | givenIds | expectedFieldWithIssue | expectedError                         | scenario
            ""            | [1, 2]   | "email"                | "must not be empty"                   | "email is empty"
            null          | [1, 2]   | "email"                | "must not be empty"                   | "email is null"
            "test"        | [1, 2]   | "email"                | "must be a well-formed email address" | "email does not have the correct format"
            "a@gmail.com" | []       | "productIds"           | "must not be empty"                   | "product id list is empty"
            "a@gmail.com" | null     | "productIds"           | "must not be empty"                    | "product id list is null"
    }
}
