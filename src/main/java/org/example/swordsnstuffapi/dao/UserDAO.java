package org.example.swordsnstuffapi.dao;

import org.springframework.stereotype.Component;

@Component
public class UserDAO {
    private UserRepository userRepository;

    public UserDAO(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long getUserIdByEmail(String email) {
//        System.out.println("Email:" + email);
        return this.userRepository.findByEmail(email).getId();
    }
}
