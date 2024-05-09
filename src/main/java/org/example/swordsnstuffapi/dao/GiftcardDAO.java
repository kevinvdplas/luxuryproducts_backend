package org.example.swordsnstuffapi.dao;

import org.example.swordsnstuffapi.models.Giftcard;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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

    public void deactivateGiftcard(long id) {
        Optional<Giftcard> optionalGiftcard = this.giftcardRepository.findById(id);
        if (optionalGiftcard.isPresent()) {
            Giftcard giftcard = optionalGiftcard.get();
            if (giftcard.isUsed()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Giftcard is al checked.");
            } else {
                giftcard.setUsed(true);
                this.giftcardRepository.save(giftcard);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Foutmelding! Giftcard met dat id bestaat niet.");
        }
    }

    public void activateGiftcard(long id) {
        Optional<Giftcard> optionalGiftcard = this.giftcardRepository.findById(id);
        if (optionalGiftcard.isPresent()) {
            Giftcard giftcard = optionalGiftcard.get();
            if (!giftcard.isUsed()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Giftcard is al unchecked.");
            } else {
                giftcard.setUsed(false);
                this.giftcardRepository.save(giftcard);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Foutmelding! Giftcard met dat id bestaat niet.");
        }
    }
}
