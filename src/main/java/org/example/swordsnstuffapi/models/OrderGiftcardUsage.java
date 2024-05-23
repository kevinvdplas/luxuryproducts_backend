package org.example.swordsnstuffapi.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class OrderGiftcardUsage {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonBackReference
    private Order order;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonBackReference
    private Giftcard giftcard;

    public OrderGiftcardUsage(Order order, Giftcard giftcard) {
        this.order = order;
        this.giftcard = giftcard;
    }

    public OrderGiftcardUsage() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Giftcard getGiftcard() {
        return giftcard;
    }

    public void setGiftcard(Giftcard giftcard) {
        this.giftcard = giftcard;
    }
}
