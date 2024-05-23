package org.example.swordsnstuffapi.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity(name = "entity_order")
public class Order {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private CustomUser customUser;

    @ManyToMany
    private List<Product> products;

    @OneToMany(mappedBy = "order", cascade = CascadeType.MERGE)
    @JsonManagedReference
    private List<OrderGiftcardUsage> orderGiftcardUsages;

    private String status = "processing";

    private double total_price;

    public Order() {

    }

    public Order(CustomUser customUser, List<Product> products, double total_price, List<OrderGiftcardUsage> orderGiftcardUsages) {
        this.customUser = customUser;
        this.products = products;
        this.total_price = total_price;
        this.orderGiftcardUsages = orderGiftcardUsages;
    }

    @Override
    public String toString () {
        return "Order{" +
                "id=" + id +
                ", customUser=" + customUser +
                ", products=" + products +
                ", status='" + status + '\'' +
                ", total_price=" + total_price +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CustomUser getCustomUser() {
        return customUser;
    }

    public void setCustomUser(CustomUser customUser) {
        this.customUser = customUser;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public List<OrderGiftcardUsage> getOrderGiftcardUsages() {
        return orderGiftcardUsages;
    }

    public void setOrderGiftcardUsages(List<OrderGiftcardUsage> orderGiftcardUsages) {
        this.orderGiftcardUsages = orderGiftcardUsages;
    }
}
