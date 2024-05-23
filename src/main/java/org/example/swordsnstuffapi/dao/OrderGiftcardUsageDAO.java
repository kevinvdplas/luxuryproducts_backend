package org.example.swordsnstuffapi.dao;

import org.example.swordsnstuffapi.models.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderGiftcardUsageDAO {
    private final OrderGiftcardUsageRepository orderGiftcardUsageRepository;
    private final OrderRepository orderRepository;

    public OrderGiftcardUsageDAO(OrderGiftcardUsageRepository orderGiftcardUsageRepository, OrderRepository orderRepository) {
        this.orderGiftcardUsageRepository = orderGiftcardUsageRepository;
        this.orderRepository = orderRepository;
    }

    public List<Order> getOrdersByGiftcard(Long giftcard_id) {
        List<Long> order_ids = this.orderGiftcardUsageRepository.findOrderIdByGiftcardId(giftcard_id);
        System.out.println(order_ids);
        return this.orderRepository.findAllById(order_ids);
    }
}
