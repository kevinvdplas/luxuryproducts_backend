package org.example.swordsnstuffapi;

import org.example.swordsnstuffapi.dao.OrderGiftcardUsageDAO;
import org.example.swordsnstuffapi.dao.OrderGiftcardUsageRepository;
import org.example.swordsnstuffapi.dao.OrderRepository;
import org.example.swordsnstuffapi.models.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class OrderGiftcardUsageTests {

    @Mock
    private OrderGiftcardUsageRepository orderGiftcardUsageRepository;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderGiftcardUsageDAO orderGiftcardUsageDAO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetOrdersByGiftcard() {
        Long giftcardId = 1L;
        List<Long> orderIds = Arrays.asList(1L, 2L);
        List<Order> orders = Arrays.asList(new Order(), new Order());

        when(orderGiftcardUsageRepository.findOrderIdByGiftcardId(giftcardId)).thenReturn(orderIds);
        when(orderRepository.findAllById(orderIds)).thenReturn(orders);

        List<Order> result = orderGiftcardUsageDAO.getOrdersByGiftcard(giftcardId);

        assertEquals(orders, result);
        verify(orderGiftcardUsageRepository, times(1)).findOrderIdByGiftcardId(giftcardId);
        verify(orderRepository, times(1)).findAllById(orderIds);
    }
}
