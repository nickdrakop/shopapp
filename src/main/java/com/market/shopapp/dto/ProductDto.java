/**
 * @author nick.drakopoulos
 */

package com.market.shopapp.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ProductDto {

    private Integer id;

    @NotEmpty
    private String name;

    @NotNull
    private Integer price; // in cents

    private Integer quantity = 1;

    public ProductDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", price=" + price +
            ", quantity=" + quantity +
            '}';
    }
}
