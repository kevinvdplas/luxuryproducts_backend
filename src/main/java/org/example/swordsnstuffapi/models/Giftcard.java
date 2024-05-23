package org.example.swordsnstuffapi.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Giftcard {
    @Id
    @GeneratedValue
    private long giftcard_id;

    private String code;
    private double price;
    private boolean isUsed = false;
    private LocalDate expirationDate;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private CustomUser customUser;

    @OneToMany(mappedBy = "giftcard", cascade = CascadeType.MERGE)
    @JsonManagedReference
    private List<OrderGiftcardUsage> orderGiftcardUsages;

    public Giftcard(String code, double price, LocalDate expirationDate, CustomUser customUser, List<OrderGiftcardUsage> orderGiftcardUsages) {
        this.code = code;
        this.price = price;
        this.expirationDate = expirationDate;
        this.customUser = customUser;
        this.orderGiftcardUsages = orderGiftcardUsages;
    }

    public Giftcard() {}

    @Override
    public String toString() {
        return "Giftcard{" +
                "id=" + giftcard_id +
                ", code='" + code + '\'' +
                ", price=" + price +
                ", isUsed=" + isUsed +
                ", expirationDate=" + expirationDate +
                '}';
    }

    public boolean isExpired() {
        return !isUsed && LocalDate.now().isAfter(expirationDate);
    }

    public void expireGiftcard() {
        if (isExpired()) {
            this.isUsed = true;
        }
    }

    public Long getUserId() {
        return customUser != null ? customUser.getId() : null;
    }

    public CustomUser getCustomUser() {
        return customUser;
    }

    public void setCustomUser(CustomUser customUser) {
        this.customUser = customUser;
    }

    public long getGiftcard_id() {
        return giftcard_id;
    }

    public void setGiftcard_id(long giftcard_id) {
        this.giftcard_id = giftcard_id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public List<OrderGiftcardUsage> getOrderGiftcardUsages() {
        return orderGiftcardUsages;
    }

    public void setOrderGiftcardUsages(List<OrderGiftcardUsage> orderGiftcardUsages) {
        this.orderGiftcardUsages = orderGiftcardUsages;
    }
}
