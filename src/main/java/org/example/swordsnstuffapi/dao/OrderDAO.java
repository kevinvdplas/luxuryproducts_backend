package org.example.swordsnstuffapi.dao;

import org.example.swordsnstuffapi.dto.OrderDTO;
import org.example.swordsnstuffapi.models.CustomUser;
import org.example.swordsnstuffapi.models.Giftcard;
import org.example.swordsnstuffapi.models.Order;
import org.example.swordsnstuffapi.models.Product;
import org.example.swordsnstuffapi.services.UserService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class OrderDAO {

    private final OrderRepository orderRepository;
    private UserRepository userRepository;
    private UserService userService;

    private GiftcardRepository giftcardRepository;

    public OrderDAO(OrderRepository orderRepository, UserRepository userRepository, UserService userService, GiftcardRepository giftcardRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.giftcardRepository = giftcardRepository;
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public List<Order> getOrdersByCustomUser (){
        CustomUser  customUser = userService.getActiveUser();
        return this.orderRepository.findAllByCustomUser(customUser);
    }

    public void createOrder(OrderDTO orderDTO){
        CustomUser customUser = userService.getActiveUser();
        System.out.println("Gebruiker:" + customUser + "OrderDTO:" + orderDTO);
        Order newOrder = new Order(
                customUser,
                orderDTO.products,
                orderDTO.total_price
        );

        for (Product product : orderDTO.products) {
            if ("Giftcard".equals(orderDTO.products.get(0).getName())) {
                Giftcard giftcard = new Giftcard("8888-8888", product.getPrice().doubleValue(), LocalDate.now().plusYears(1), customUser);
                this.giftcardRepository.save(giftcard);
            }
        }

        this.orderRepository.save(newOrder);
    }
}
