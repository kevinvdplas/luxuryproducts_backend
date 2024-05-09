package org.example.swordsnstuffapi.controller;

import org.example.swordsnstuffapi.dao.GiftcardDAO;
import org.example.swordsnstuffapi.dao.GiftcardRepository;
import org.example.swordsnstuffapi.models.Giftcard;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
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

    @PutMapping("/deactivate/{id}")
    public ResponseEntity<String> checkGiftcard(@PathVariable long id) {
        this.giftcardDAO.deactivateGiftcard(id);
        return ResponseEntity.ok("Deactivated giftcard with id " + id);
    }
}
