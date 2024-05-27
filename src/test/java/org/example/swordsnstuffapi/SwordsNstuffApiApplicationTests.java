package org.example.swordsnstuffapi;

import org.example.swordsnstuffapi.dao.GiftcardDAO;
import org.example.swordsnstuffapi.dao.GiftcardRepository;
import org.example.swordsnstuffapi.dao.UserDAO;
import org.example.swordsnstuffapi.dto.GiftcardDTO;
import org.example.swordsnstuffapi.models.Giftcard;
import org.example.swordsnstuffapi.services.MailSenderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SwordsNstuffApiApplicationTests {

    @Mock
    private GiftcardRepository giftcardRepository;

    @Mock
    private MailSenderService mailService;

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private GiftcardDAO giftcardDAO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllGiftcards() {
        List<Giftcard> giftcards = Arrays.asList(new Giftcard(), new Giftcard());
        when(giftcardRepository.findAll()).thenReturn(giftcards);

        List<Giftcard> result = giftcardDAO.getAllGiftcards();

        assertEquals(2, result.size());
        verify(giftcardRepository, times(1)).findAll();
    }

    @Test
    public void testGetGiftcardsByEmail() {
        long userId = 1L;
        String email = "test@example.com";
        List<Giftcard> giftcards = Arrays.asList(new Giftcard(), new Giftcard());

        when(userDAO.getUserIdByEmail(email)).thenReturn(userId);
        when(giftcardRepository.findByCustomUser_Id(userId)).thenReturn(giftcards);

        List<Giftcard> result = giftcardDAO.getGiftcardsByEmail(email);

        assertEquals(2, result.size());
        verify(userDAO, times(1)).getUserIdByEmail(email);
        verify(giftcardRepository, times(1)).findByCustomUser_Id(userId);
    }

    @Test
    public void testGetGiftcardByCode() {
        String code = "ABCD-1234";
        Giftcard giftcard = new Giftcard();
        when(giftcardRepository.findByCode(code)).thenReturn(Optional.of(giftcard));

        Giftcard result = giftcardDAO.getGiftcardByCode(code);

        assertNotNull(result);
        verify(giftcardRepository, times(1)).findByCode(code);
    }

    @Test
    public void testGetGiftcardByCode_NotFound() {
        String code = "ABCD-1234";
        when(giftcardRepository.findByCode(code)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> giftcardDAO.getGiftcardByCode(code));
    }

    @Test
    public void testDeactivateGiftcard() {
        long id = 1L;
        Giftcard giftcard = new Giftcard();
        giftcard.setUsed(false);
        when(giftcardRepository.findById(id)).thenReturn(Optional.of(giftcard));

        giftcardDAO.deactivateGiftcard(id);

        assertTrue(giftcard.isUsed());
        verify(giftcardRepository, times(1)).findById(id);
        verify(giftcardRepository, times(1)).save(giftcard);
    }

    @Test
    public void testDeactivateGiftcard_AlreadyUsed() {
        long id = 1L;
        Giftcard giftcard = new Giftcard();
        giftcard.setUsed(true);
        when(giftcardRepository.findById(id)).thenReturn(Optional.of(giftcard));

        assertThrows(ResponseStatusException.class, () -> giftcardDAO.deactivateGiftcard(id));
    }

    @Test
    public void testDeactivateGiftcard_NotFound() {
        long id = 1L;
        when(giftcardRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> giftcardDAO.deactivateGiftcard(id));
    }

    @Test
    public void testActivateGiftcard() {
        long id = 1L;
        Giftcard giftcard = new Giftcard();
        giftcard.setUsed(true);
        when(giftcardRepository.findById(id)).thenReturn(Optional.of(giftcard));

        giftcardDAO.activateGiftcard(id);

        assertFalse(giftcard.isUsed());
        verify(giftcardRepository, times(1)).findById(id);
        verify(giftcardRepository, times(1)).save(giftcard);
    }

    @Test
    public void testActivateGiftcard_NotUsed() {
        long id = 1L;
        Giftcard giftcard = new Giftcard();
        giftcard.setUsed(false);
        when(giftcardRepository.findById(id)).thenReturn(Optional.of(giftcard));

        assertThrows(ResponseStatusException.class, () -> giftcardDAO.activateGiftcard(id));
    }

    @Test
    public void testActivateGiftcard_NotFound() {
        long id = 1L;
        when(giftcardRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> giftcardDAO.activateGiftcard(id));
    }
}