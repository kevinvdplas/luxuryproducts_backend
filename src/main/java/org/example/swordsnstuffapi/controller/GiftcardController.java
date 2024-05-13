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

    @GetMapping()
    public ResponseEntity<List<Giftcard>> getAllGiftcards() {
        List<Giftcard> giftcards = this.giftcardDAO.getAllGiftcards();
        return ResponseEntity.ok(giftcards);
    }

    @GetMapping("/{email}")
    public ResponseEntity<List<Giftcard>> getGiftcardsByEmail(@PathVariable String email) {
        List<Giftcard> giftcards = this.giftcardDAO.getGiftcardsByEmail(email);
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
        System.out.println(giftcardDTO);
        this.giftcardDAO.updateSaldoToGiftcard(code, giftcardDTO);
        return ResponseEntity.ok("Updated saldo on giftcard " + code);
    }
}
