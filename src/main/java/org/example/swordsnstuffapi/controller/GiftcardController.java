package org.example.swordsnstuffapi.controller;

import org.example.swordsnstuffapi.dao.GiftcardDAO;
import org.example.swordsnstuffapi.dao.GiftcardRepository;
import org.example.swordsnstuffapi.models.Giftcard;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/giftcards")
public class GiftcardController {
    private GiftcardDAO giftcardDAO;

    public GiftcardController(GiftcardDAO giftcardDAO) {
        this.giftcardDAO = giftcardDAO;
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<List<Giftcard>> getGiftcardById(@PathVariable long id) {
//        List<Giftcard> giftcards = this.giftcardDAO.getGiftcardById(id);
//        return ResponseEntity.ok(giftcards);
//    }

    @GetMapping()
    public ResponseEntity<List<Giftcard>> getAllGiftcards() {
        List<Giftcard> giftcards = this.giftcardDAO.getAllGiftcards();
        return ResponseEntity.ok(giftcards);
    }
}
