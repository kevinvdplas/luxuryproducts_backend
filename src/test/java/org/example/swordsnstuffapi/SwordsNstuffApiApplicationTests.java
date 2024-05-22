package org.example.swordsnstuffapi;

import org.example.swordsnstuffapi.dao.GiftcardDAO;
import org.example.swordsnstuffapi.dao.GiftcardRepository;
import org.example.swordsnstuffapi.dao.UserDAO;
import org.example.swordsnstuffapi.services.MailSenderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class SwordsNstuffApiApplicationTests {

    private GiftcardDAO giftcardDAO;

    @BeforeEach
    public void initTest() {
        GiftcardRepository giftcardRepository = Mockito.mock(GiftcardRepository.class);
        MailSenderService mailSenderService = Mockito.mock(MailSenderService.class);
        UserDAO userDAO = Mockito.mock(UserDAO.class);

        this.giftcardDAO = new GiftcardDAO(giftcardRepository, mailSenderService, userDAO);
    }

    @Test
    public void should_check_if_giftcard_gets_deactivated() {

    }

}
