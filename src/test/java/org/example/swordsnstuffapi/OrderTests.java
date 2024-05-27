package org.example.swordsnstuffapi;

import org.example.swordsnstuffapi.dao.*;
import org.example.swordsnstuffapi.dto.OrderDTO;
import org.example.swordsnstuffapi.models.*;
import org.example.swordsnstuffapi.services.MailSenderService;
import org.example.swordsnstuffapi.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrderTests {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserService userService;

    @Mock
    private GiftcardRepository giftcardRepository;

    @Mock
    private MailSenderService mailService;

    @Mock
    private GiftcardDAO giftcardDAO;

    @Mock
    private OrderGiftcardUsageRepository orderGiftcardUsageRepository;

    @InjectMocks
    private OrderDAO orderDAO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private Product createProduct(String name, Number price) {
        return new Product("thumbnail", name, "description", price, 1, null);
    }

    @Test
    public void testGetAllOrders() {
        List<Order> orders = Arrays.asList(new Order(), new Order());
        when(orderRepository.findAll()).thenReturn(orders);

        List<Order> result = orderDAO.getAllOrders();

        assertEquals(orders, result);
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    public void testGetOrdersByCustomUser() {
        CustomUser customUser = new CustomUser();
        List<Order> orders = Arrays.asList(new Order(), new Order());
        when(userService.getActiveUser()).thenReturn(customUser);
        when(orderRepository.findAllByCustomUser(customUser)).thenReturn(orders);

        List<Order> result = orderDAO.getOrdersByCustomUser();

        assertEquals(orders, result);
        verify(userService, times(1)).getActiveUser();
        verify(orderRepository, times(1)).findAllByCustomUser(customUser);
    }

    @Test
    public void testCreateOrder() {
        CustomUser customUser = new CustomUser();
        OrderDTO orderDTO = new OrderDTO(
                Arrays.asList(createProduct("Giftcard", 50.0), createProduct("Sword", 150.0)),
                200.0,
                "OrderName"
        );

        when(userService.getActiveUser()).thenReturn(customUser);
        when(giftcardDAO.createGiftcard()).thenReturn("CODE1234");
        when(giftcardRepository.existsByCode("CODE1234")).thenReturn(false);

        Giftcard mockGiftcard = new Giftcard("CODE1234", 50.0, LocalDate.now().plusYears(1), customUser, null);
        when(giftcardRepository.findById(1L)).thenReturn(Optional.of(mockGiftcard));

        orderDAO.createOrder(orderDTO, Arrays.asList("1"));

        ArgumentCaptor<Order> orderCaptor = ArgumentCaptor.forClass(Order.class);
        verify(orderRepository, times(1)).save(orderCaptor.capture());
        Order savedOrder = orderCaptor.getValue();
        assertEquals(orderDTO.total_price, savedOrder.getTotal_price());
        assertEquals(customUser, savedOrder.getCustomUser());

        ArgumentCaptor<Giftcard> giftcardCaptor = ArgumentCaptor.forClass(Giftcard.class);
        verify(giftcardRepository, times(1)).save(giftcardCaptor.capture());
        Giftcard savedGiftcard = giftcardCaptor.getValue();
        assertEquals("CODE1234", savedGiftcard.getCode());
        assertEquals(50.0, savedGiftcard.getPrice());

        verify(mailService, times(1)).sendNewMail(eq(customUser.getEmail()), eq("Webshop Bob giftcard code"), anyString());

        ArgumentCaptor<OrderGiftcardUsage> usageCaptor = ArgumentCaptor.forClass(OrderGiftcardUsage.class);
        verify(orderGiftcardUsageRepository, times(1)).save(usageCaptor.capture());
        OrderGiftcardUsage savedUsage = usageCaptor.getValue();
        assertEquals(savedOrder, savedUsage.getOrder());
        assertEquals(savedGiftcard, savedUsage.getGiftcard());
    }
}
