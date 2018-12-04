/**
 * @author nick.drakopoulos
 */

package com.market.shopapp.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customer_order")
public class CustomerOrderEntity extends AbstractEntity<Integer>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "email")
    private String email;

    @Column(name = "total_order_value")
    private Integer totalOrderValue; //in cents

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(name = "customer_order_product",
        joinColumns = { @JoinColumn(name = "customer_order_id") },
        inverseJoinColumns = { @JoinColumn(name = "product_id") }
    )
    private Set<ProductEntity> products = new HashSet<>();

    public CustomerOrderEntity() {
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
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

    public Set<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductEntity> products) {
        this.products = products;
    }
}
