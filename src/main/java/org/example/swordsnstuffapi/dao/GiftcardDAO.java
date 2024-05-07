package org.example.swordsnstuffapi.dao;

import org.example.swordsnstuffapi.models.Giftcard;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GiftcardDAO {
    private GiftcardRepository giftcardRepository;

    public GiftcardDAO(GiftcardRepository giftcardRepository) {
        this.giftcardRepository = giftcardRepository;
    }

//    public List<Giftcard> getGiftcardById(long id) {
//        return this.giftcardRepository.findById(id);
//    }

    public List<Giftcard> getAllGiftcards() {
        return this.giftcardRepository.findAll();
    }
}
