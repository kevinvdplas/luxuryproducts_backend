package org.example.swordsnstuffapi.dao;

import org.example.swordsnstuffapi.dto.GiftcardDTO;
import org.example.swordsnstuffapi.models.Giftcard;
import org.example.swordsnstuffapi.services.MailSenderService;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Component
public class GiftcardDAO {
    private GiftcardRepository giftcardRepository;
    private MailSenderService mailService;
    private UserDAO userDAO;

    public GiftcardDAO(GiftcardRepository giftcardRepository, MailSenderService mailService, UserDAO userDAO) {
        this.giftcardRepository = giftcardRepository;
        this.mailService = mailService;
        this.userDAO = userDAO;
    }

//    public List<Giftcard> getGiftcardById(long id) {
//        return this.giftcardRepository.findById(id);
//    }

    public List<Giftcard> getAllGiftcards() {
        return this.giftcardRepository.findAll();
    }

    public List<Giftcard> getGiftcardsByEmail(String email) {
        System.out.println("Email:" + email);
        long user_id = userDAO.getUserIdByEmail(email);
        return this.giftcardRepository.findByCustomUser_Id(user_id);
    }

    public Giftcard getGiftcardByCode(String code) {
        Optional<Giftcard> optionalGiftcard = this.giftcardRepository.findByCode(code);
        if (optionalGiftcard.isPresent()) {
            return optionalGiftcard.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Foutmelding! Giftcard met die code bestaat niet.");
        }
    }

    public void deactivateGiftcard(long id) {
        Optional<Giftcard> optionalGiftcard = this.giftcardRepository.findById(id);
        if (optionalGiftcard.isPresent()) {
            Giftcard giftcard = optionalGiftcard.get();
            if (giftcard.isUsed()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Giftcard is al checked.");
            } else {
                giftcard.setUsed(true);
                this.giftcardRepository.save(giftcard);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Foutmelding! Giftcard met dat id bestaat niet.");
        }
    }

    public void activateGiftcard(long id) {
        Optional<Giftcard> optionalGiftcard = this.giftcardRepository.findById(id);
        if (optionalGiftcard.isPresent()) {
            Giftcard giftcard = optionalGiftcard.get();
            if (!giftcard.isUsed()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Giftcard is al unchecked.");
            } else {
                giftcard.setUsed(false);
                this.giftcardRepository.save(giftcard);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Foutmelding! Giftcard met dat id bestaat niet.");
        }
    }

    public String createGiftcard() {
        Random random = new Random();
        StringBuilder codeBuilder = new StringBuilder();

        String code;
        boolean isUnique = false;

        char[] alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

        do {
            for (int i = 0; i < 8; i++) {
                codeBuilder.append(alphabet[random.nextInt(alphabet.length)]);
                if (i == 3) {
                    codeBuilder.append("-");
                }
            }
            code = codeBuilder.toString();
            if (!this.giftcardRepository.existsByCode(code)) {
                isUnique = true;
            }
        } while (!isUnique);
        return code;
    }

    @Scheduled(cron = "0 0 1 * * *", zone = "CET")
    public void expireGiftcards() {
        List<Giftcard> giftcards = this.giftcardRepository.findAll();
        for (Giftcard giftcard : giftcards) {
            if(giftcard.isExpired()) {
                giftcard.expireGiftcard();
                this.giftcardRepository.save(giftcard);
            }
        }
    }

    public void updateSaldoToGiftcard(String code, GiftcardDTO giftcardDTO) {
        Optional<Giftcard> optionalGiftcard = this.giftcardRepository.findByCode(code);
        if (optionalGiftcard.isPresent()) {
            Giftcard giftcard = optionalGiftcard.get();
            double currentSaldo = giftcard.getPrice();
            currentSaldo += giftcardDTO.price;
            double newSaldo = currentSaldo;
            giftcard.setPrice(newSaldo);
            this.giftcardRepository.save(giftcard);
        }
    }
}
