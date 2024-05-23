package org.example.swordsnstuffapi.controller;

import org.example.swordsnstuffapi.dao.OrderGiftcardUsageDAO;
import org.example.swordsnstuffapi.models.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "http://s1149501.student.inf-hsleiden.nl:19501"})
@RequestMapping("/order-giftcard-usage")
public class OrderGiftcardUsageController {

    private final OrderGiftcardUsageDAO orderGiftcardUsageDAO;

    public OrderGiftcardUsageController(OrderGiftcardUsageDAO orderGiftcardUsageDAO) {
        this.orderGiftcardUsageDAO = orderGiftcardUsageDAO;
    }

    @GetMapping("/{giftcard_id}")
    public ResponseEntity<List<Order>> getOrderGiftcardUsage(@PathVariable Long giftcard_id) {
        return ResponseEntity.ok(this.orderGiftcardUsageDAO.getOrdersByGiftcard(giftcard_id));
    }
}
