package org.example.swordsnstuffapi.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Set;

@Entity(name = "custom_user")
public class CustomUser {
     @Id
     @GeneratedValue
     private long id;
     private String email;
     private String password;
     private String firstName;
     private String lastName;
     private boolean admin = false;

    @JsonManagedReference
    @OneToMany(mappedBy = "customUser")
    private Set<Giftcard> giftcards;

    public CustomUser() {
    }

    public CustomUser(String email, String password, String firstName, String lastName, boolean admin) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "CustomUser{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public Set<Giftcard> getGiftcards() {
        return giftcards;
    }

    public void setGiftcards(Set<Giftcard> giftcards) {
        this.giftcards = giftcards;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
