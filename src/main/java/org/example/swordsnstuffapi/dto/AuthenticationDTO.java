package org.example.swordsnstuffapi.dto;

public class AuthenticationDTO {
    public String email;
    public String firstName;
    public String lastName;
    public String password;
    public boolean admin;

    public AuthenticationDTO(String email, String password, String firstName, String lastName, boolean admin) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.admin = admin;
    }
}
