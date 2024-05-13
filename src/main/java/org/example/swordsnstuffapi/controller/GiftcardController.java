package org.example.swordsnstuffapi.controller;

import org.example.swordsnstuffapi.dao.GiftcardDAO;
import org.example.swordsnstuffapi.dao.GiftcardRepository;
import org.example.swordsnstuffapi.dto.GiftcardDTO;
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

    @GetMapping("/code/{code}")
    public ResponseEntity<Giftcard> getGiftcardByCode(@PathVariable String code) {
        Giftcard giftcard = this.giftcardDAO.getGiftcardByCode(code);
        return ResponseEntity.ok(giftcard);
    }

    @PutMapping("/deactivate/{id}")
    public ResponseEntity<String> deactivateGiftcard(@PathVariable long id) {
        this.giftcardDAO.deactivateGiftcard(id);
        return ResponseEntity.ok("Deactivated giftcard with id " + id);
    }

    @PutMapping("/activate/{id}")
    public ResponseEntity<String> activateGiftcard(@PathVariable long id) {
        this.giftcardDAO.activateGiftcard(id);
        return ResponseEntity.ok("Activated giftcard with id " + id);
    }

    @PutMapping("/updateSaldo/{code}")
    public ResponseEntity<String> updateSaldoToGiftcard(@PathVariable String code, @RequestBody GiftcardDTO giftcardDTO) {
        this.giftcardDAO.updateSaldoToGiftcard(code, giftcardDTO);
        return ResponseEntity.ok("Updated saldo on giftcard " + code);
    }
}
