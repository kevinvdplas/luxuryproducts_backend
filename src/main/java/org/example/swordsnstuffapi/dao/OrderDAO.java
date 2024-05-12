package org.example.swordsnstuffapi.dao;

import org.example.swordsnstuffapi.dto.OrderDTO;
import org.example.swordsnstuffapi.models.CustomUser;
import org.example.swordsnstuffapi.models.Giftcard;
import org.example.swordsnstuffapi.models.Order;
import org.example.swordsnstuffapi.models.Product;
import org.example.swordsnstuffapi.services.MailSenderService;
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
    private MailSenderService mailService;

    public OrderDAO(OrderRepository orderRepository, UserRepository userRepository, UserService userService, GiftcardRepository giftcardRepository, MailSenderService mailService) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.giftcardRepository = giftcardRepository;
        this.mailService = mailService;
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
                mailService.sendNewMail(customUser.getEmail(), "Webshop Bob giftcard code", "Hier is de code ter waarde van €" + product.getPrice() + "\n" + "Code: 8888-8888");
                this.giftcardRepository.save(giftcard);
            }
        }

        this.orderRepository.save(newOrder);
    }
}
