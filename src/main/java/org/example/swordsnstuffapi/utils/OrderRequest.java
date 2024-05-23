package org.example.swordsnstuffapi.utils;

import org.example.swordsnstuffapi.dto.GiftcardDTO;
import org.example.swordsnstuffapi.dto.OrderDTO;

import java.security.SecureRandom;
import java.util.List;

public class OrderRequest {
    private OrderDTO order;
    private List<String> giftcards;

    public OrderRequest(OrderDTO order, List<String> giftcards) {
        this.order = order;
        this.giftcards = giftcards;
    }

    public OrderDTO getOrderDTO() {
        return order;
    }

    public List<String> getGiftcardDTO() {
        return giftcards;
    }

    public void setOrderDTO(OrderDTO order) {
        this.order = order;
    }

    public void setGiftcardDTO(List<String> giftcards) {
        this.giftcards = giftcards;
    }
}
