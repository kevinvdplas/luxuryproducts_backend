package org.example.swordsnstuffapi.dto;

public class GiftcardDTO {
    public double price;
    public String code;
    public int id;

    public GiftcardDTO(double price, String code, int id) {
        this.price = price;
        this.id = id;
        this.code = code;
    }

    @Override
    public String toString() {
        return "GiftcardDTO{" +
                "price=" + price +
                ", id=" + id +
                '}';
    }

    public String getCode() {
        return code;
    }
}