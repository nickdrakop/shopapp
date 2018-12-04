/**
 * @author nick.drakopoulos
 */

package com.market.shopapp.dto;

import com.market.shopapp.domain.ProductEntity;

import javax.validation.constraints.NotEmpty;
import java.time.Instant;
import java.util.Optional;
import java.util.Set;

public class CustomerOrderDto {

    private Integer id;

    @NotEmpty
    private String email;

    private Integer totalOrderValue; //in cents

    private Instant orderCreatedAt;

    @NotEmpty
    private Set<ProductEntity> products;

    public CustomerOrderDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getTotalOrderValue() {
        if(Optional.ofNullable(products).isPresent()){
            return products.stream()
                .map(ProductEntity::getPrice)
                .mapToInt(Integer::intValue)
                .sum();
        }
        return 0;
    }

    public void setTotalOrderValue(Integer totalOrderValue) {
        this.totalOrderValue = totalOrderValue;
    }

    public Instant getOrderCreatedAt() {
        return orderCreatedAt;
    }

    public void setOrderCreatedAt(Instant orderCreatedAt) {
        this.orderCreatedAt = orderCreatedAt;
    }

    public Set<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductEntity> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "CustomerOrderDto{" +
            "id=" + id +
            ", email='" + email + '\'' +
            ", totalOrderValue=" + totalOrderValue +
            ", orderCreatedAt=" + orderCreatedAt +
            ", products=" + products +
            '}';
    }
}
