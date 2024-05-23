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
    private GiftcardDAO GiftcardDAO;

    public OrderDAO(OrderRepository orderRepository, UserRepository userRepository, UserService userService, GiftcardRepository giftcardRepository, MailSenderService mailService, GiftcardDAO GiftcardDAO) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.giftcardRepository = giftcardRepository;
        this.mailService = mailService;
        this.GiftcardDAO = GiftcardDAO;
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
        Order newOrder = new Order(
                customUser,
                orderDTO.products,
                orderDTO.total_price,
                null
        );

        for (Product product : orderDTO.products) {
            if ("Giftcard".equals(product.getName())) {
                String code = GiftcardDAO.createGiftcard();
                Giftcard giftcard = new Giftcard(code, product.getPrice().doubleValue(), LocalDate.now().plusYears(1), customUser, null);
                mailService.sendNewMail(customUser.getEmail(), "Webshop Bob giftcard code", "Hier is de code ter waarde van €" + product.getPrice() + "\n" + "Code: " + code);
                this.giftcardRepository.save(giftcard);
            }
        }

        this.orderRepository.save(newOrder);
    }
}
