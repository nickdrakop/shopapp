/**
 @author nick.drakopoulos
 */
package com.market.shopapp.helper

import com.market.shopapp.domain.ProductEntity

import java.time.Instant

trait ProductHelper {

    List<ProductEntity> createSomeProductEntities(Instant someInstant) {
        ProductEntity productEntity1 = new ProductEntity(id: 1, name: "product1", price: 1400, quantity: 1, createdAt: someInstant)
        ProductEntity productEntity2 = new ProductEntity(id: 2, name: "product2", price: 1600, quantity: 1, createdAt: someInstant)
        [productEntity1, productEntity2]
    }
}
