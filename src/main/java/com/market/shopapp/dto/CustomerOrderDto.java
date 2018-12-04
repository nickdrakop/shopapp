/**
 * @author nick.drakopoulos
 */

package com.market.shopapp.dto;

import javax.validation.constraints.NotEmpty;
import java.time.Instant;
import java.util.List;

public class CustomerOrderDto {

    private Integer id;

    @NotEmpty
    private String email;

    private Integer totalOrderValue; //in cents

    private Instant orderCreatedAt;

    @NotEmpty
    private List<Integer> productIds;

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
        return totalOrderValue;
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

    public List<Integer> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Integer> productIds) {
        this.productIds = productIds;
    }

    @Override
    public String toString() {
        return "CustomerOrderDto{" +
                ", email='" + email + '\'' +
                ", productIds=" + productIds +
                '}';
    }
}
