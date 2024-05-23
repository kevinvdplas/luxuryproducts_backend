package org.example.swordsnstuffapi.controller;

import org.example.swordsnstuffapi.dao.OrderDAO;
import org.example.swordsnstuffapi.dto.GiftcardDTO;
import org.example.swordsnstuffapi.dto.OrderDTO;
import org.example.swordsnstuffapi.models.Order;
import org.example.swordsnstuffapi.utils.OrderRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "http://s1149501.student.inf-hsleiden.nl:19501"})
@RequestMapping("/orders")
public class OrderController {

    private final OrderDAO orderDAO;

    public OrderController(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @GetMapping
    public ResponseEntity<List<Order>> getOrdersByCustomUser(){
        return ResponseEntity.ok(this.orderDAO.getOrdersByCustomUser());
    }

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderRequest orderRequest){
        System.out.println("Order: " + orderRequest.getOrderDTO() + " Giftcard: " + orderRequest.getGiftcardDTO());
        this.orderDAO.createOrder(orderRequest.getOrderDTO(), orderRequest.getGiftcardDTO());
        return ResponseEntity.ok("new order has been created");
    }

}
