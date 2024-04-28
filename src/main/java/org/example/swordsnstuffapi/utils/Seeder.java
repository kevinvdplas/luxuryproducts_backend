package org.example.swordsnstuffapi.utils;

import org.example.swordsnstuffapi.dao.CategoryRepository;
import org.example.swordsnstuffapi.dao.OrderRepository;
import org.example.swordsnstuffapi.dao.ProductRepository;
import org.example.swordsnstuffapi.dao.UserRepository;
import org.example.swordsnstuffapi.models.Category;
import org.example.swordsnstuffapi.models.CustomUser;
import org.example.swordsnstuffapi.models.Order;
import org.example.swordsnstuffapi.models.Product;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class Seeder {

    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private OrderRepository orderRepository;

    public Seeder(CategoryRepository categoryRepository, ProductRepository productRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, OrderRepository orderRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.orderRepository = orderRepository;
    }

    @EventListener
    public void seeder(ContextRefreshedEvent event){

        String encodedPassword = passwordEncoder.encode("Ireallylovepupp1es");
        CustomUser customer = new CustomUser(
                "test@mail.com",
                encodedPassword,
                "bob",
                "webshop"
        );
        this.userRepository.save(customer);
// Definieer luxe categorieën
        Category categoryLuxuryWatches = new Category("Luxury Watches");
        Category categoryDesignerHandbags = new Category("Designer Handbags");
        Category categoryExclusiveWines = new Category("Exclusive Wines");
        Category categoryGiftcards = new Category("Giftcards");

// Sla de luxe categorieën op
        this.categoryRepository.save(categoryLuxuryWatches);
        this.categoryRepository.save(categoryDesignerHandbags);
        this.categoryRepository.save(categoryExclusiveWines);
        this.categoryRepository.save(categoryGiftcards);

// Definieer luxe producten met nieuwe prijzen en beschrijvingen
        Product rolexWatch = new Product("URL_TO_IMAGE", "Rolex Submariner", "Iconic diver's watch known for its resistance and elegance, preferred by affluent professionals.", 10500.00, 5, categoryLuxuryWatches);
        Product louisVuittonHandbag = new Product("URL_TO_IMAGE", "Louis Vuitton Neverfull", "Stylish and roomy handbag, a must-have for any high-end fashion enthusiast's collection.", 3200.00, 4, categoryDesignerHandbags);
        Product chateauMargaux = new Product("URL_TO_IMAGE", "Château Margaux 1990", "One of the most prestigious wines from Bordeaux, perfect for sophisticated palates and special occasions.", 6900.00, 2, categoryExclusiveWines);
        Product giftcard = new Product("URL_TO_IMAGE", "Giftcard", "Giftcard for the webshop", 50.00, null, categoryGiftcards);
// Sla de luxe producten op
        this.productRepository.save(rolexWatch);
        this.productRepository.save(louisVuittonHandbag);
        this.productRepository.save(chateauMargaux);
        this.productRepository.save(giftcard);

// Voorbeeld van een order met luxe producten
        List<Product> luxuryProducts = Arrays.asList(rolexWatch, louisVuittonHandbag);
        Order luxuryOrder = new Order(
                customer,
                luxuryProducts,
                40893274
        );

// Sla de order op
        this.orderRepository.save(luxuryOrder);
    }
}
