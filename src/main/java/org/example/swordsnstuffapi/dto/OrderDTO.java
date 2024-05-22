package org.example.swordsnstuffapi.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import org.example.swordsnstuffapi.models.Product;

import java.util.ArrayList;
import java.util.List;

public class OrderDTO {

    public List<Product> products;

    public double total_price;

    public String name;


    public OrderDTO(List<Product> products, double total_price, String name) {
        this.products = products;
        this.total_price = total_price;
        this.name = name;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "products=" + products +
                ", total_price=" + total_price +
                '}';
    }
}
