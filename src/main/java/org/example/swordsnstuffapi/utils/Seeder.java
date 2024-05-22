package org.example.swordsnstuffapi.utils;

import org.example.swordsnstuffapi.dao.*;
import org.example.swordsnstuffapi.models.*;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
public class Seeder {

    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private OrderRepository orderRepository;
    private GiftcardRepository giftcardRepository;

    public Seeder(CategoryRepository categoryRepository, ProductRepository productRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, OrderRepository orderRepository, GiftcardRepository giftcardRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.orderRepository = orderRepository;
        this.giftcardRepository = giftcardRepository;
    }

    @EventListener
    public void seeder(ContextRefreshedEvent event){
        String encodedPassword = passwordEncoder.encode("Ireallylovepupp1es");

        CustomUser customer = new CustomUser(
                "test@mail.com",
                encodedPassword,
                "bob",
                "webshop",
                true
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
        Product rolexWatch = new Product("https://schapi-products.s3.eu-central-1.amazonaws.com/3523792/m126610ln-0001_modelpage_front_facing_landscape.png", "Rolex Submariner", "Iconic diver's watch known for its resistance and elegance, preferred by affluent professionals.", 10500.00, 5, categoryLuxuryWatches);
        Product louisVuittonHandbag = new Product("https://en.louisvuitton.com/images/is/image/lv/1/PP_VP_L/louis-vuitton-neverfull-mm-monogram-canvas-handbags--M41178_PM2_Front%20view.jpg", "Louis Vuitton Neverfull", "Stylish and roomy handbag, a must-have for any high-end fashion enthusiast's collection.", 3200.00, 4, categoryDesignerHandbags);
        Product chateauMargaux = new Product("https://static.millesima.com/s3/attachements/h531px/1001_2012NM_c.png", "Château Margaux 1990", "One of the most prestigious wines from Bordeaux, perfect for sophisticated palates and special occasions.", 6900.00, 2, categoryExclusiveWines);

        Product giftcard1 = new Product("https://www.yourgift.nl/wp-content/uploads/2021/11/Your-luxury-giftcard.png", "Giftcard", "Giftcard for the webshop", 10, null, categoryGiftcards);
        Product giftcard2 = new Product("https://www.yourgift.nl/wp-content/uploads/2021/11/Your-luxury-giftcard.png", "Giftcard", "Giftcard for the webshop", 25, null, categoryGiftcards);
        Product giftcard3 = new Product("https://www.yourgift.nl/wp-content/uploads/2021/11/Your-luxury-giftcard.png", "Giftcard", "Giftcard for the webshop", 50, null, categoryGiftcards);
        Product giftcard4 = new Product("https://www.yourgift.nl/wp-content/uploads/2021/11/Your-luxury-giftcard.png", "Giftcard", "Giftcard for the webshop", 100, null, categoryGiftcards);
        Product giftcard5 = new Product("https://www.yourgift.nl/wp-content/uploads/2021/11/Your-luxury-giftcard.png", "Giftcard", "Giftcard for the webshop", 250, null, categoryGiftcards);

        // Sla de luxe producten op
        this.productRepository.save(rolexWatch);
        this.productRepository.save(louisVuittonHandbag);
        this.productRepository.save(chateauMargaux);
        this.productRepository.save(giftcard1);
        this.productRepository.save(giftcard2);
        this.productRepository.save(giftcard3);
        this.productRepository.save(giftcard4);
        this.productRepository.save(giftcard5);

        // Voorbeeld van een order met luxe producten
        List<Product> luxuryProducts = Arrays.asList(rolexWatch, louisVuittonHandbag);
        Order luxuryOrder = new Order(
                customer,
                luxuryProducts,
                40893274
        );

        // Sla de order op
        this.orderRepository.save(luxuryOrder);

        CustomUser user = userRepository.findById(1L).orElse(null);
        Giftcard giftcard7 = new Giftcard("1234-5678", 69.00, LocalDate.now().plusYears(1), user);

        this.giftcardRepository.save(giftcard7);
    }
}
