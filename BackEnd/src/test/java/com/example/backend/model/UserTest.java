package com.example.backend.model;
import com.example.backend.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserTest {

    public static User user;

    @BeforeAll
    public static void initAll() {
        System.out.println("Initialization");
        user = new User();
    }

    @BeforeEach
    void init() {
        Integer id = 96;
        String name = "AAAAG";
        String password = "2153512";
        String email = "malecifent@gmail.com";
        String surname = "SOKKA";
        String gender = "N/A";

        user.setId(id);
        user.setName(name);
        user.setPassword(password);
        user.setEmail(email);
        user.setSurname(surname);
        user.setGender(gender);

    }

    @AfterAll
    public static void cleanUp() {

    }

    @Test
    void getId() {
        Integer id = 96;
        assertEquals(id, user.getId());
    }

    @Test
    void setId() {
        Integer newId = 43;
        user.setId(newId);
        assertEquals(newId, user.getId());
    }

    @Test
    void getName() {
        String name = "AAAAG";
        assertEquals(name, user.getName());
    }

    @Test
    void setName() {        String newName = "Joseph";
        user.setName(newName);
        assertEquals(newName, user.getName());

    }

    @Test
    void getSurname() {
        String surname = "SOKKA";
        assertEquals(surname, user.getSurname());


    }

    @Test
    void setSurname() {
        String newSurname = "Joestar";
        user.setSurname(newSurname);
        assertEquals(newSurname, user.getSurname());

    }

    @Test
    void getGender() {
        String gender = "N/A";
        assertEquals(gender, user.getGender());

    }

    @Test
    void setGender() {
        String newGender = "Male";
        user.setGender(newGender);
        assertEquals(newGender, user.getGender());
    }

    @Test
    void getEmail() {
        String email = "malecifent@gmail.com";
        assertEquals(email, user.getEmail());
    }

    @Test
    void setEmail() {
        String newEmail = "heracles@gmail.com"; user.setEmail(newEmail);
        assertEquals(newEmail, user.getEmail());

    }

    @Test
    void getPassword() {
        String password = "2153512";
        assertEquals(password, user.getPassword());


    }

    @Test
    void setPassword() {
        String newPassword = "30295b82385mb2";
        user.setPassword(newPassword);
        assertEquals(newPassword, user.getPassword());
    }
}