package org.example.swordsnstuffapi.dto;

public class GiftcardDTO {
    public double price;
    public int id;

    public GiftcardDTO(double price, int id) {
        this.price = price;
        this.id = id;
    }

    @Override
    public String toString() {
        return "GiftcardDTO{" +
                "price=" + price +
                ", id=" + id +
                '}';
    }
}