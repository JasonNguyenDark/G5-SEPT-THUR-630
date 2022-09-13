package com.example.backend.stuff;
import org.junit.jupiter.api.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest
class LoginTest {

    Login login;
    @BeforeEach
    void init() {
        login = new Login();

        String email = "508234750324rtfjkigbfc@gmail.com";
        String password = "58974325rdfknggdfi@gmail.com";
        login.setPassword(password);
        login.setEmail(email);
    }

    @Test
    void returnEmail() {
        String email = "508234750324rtfjkigbfc@gmail.com";
        assertEquals(email, login.getEmail(), "getEmail ");
    }

    @Test
    void returnPassword() {
        String password = "58974325rdfknggdfi@gmail.com";
        assertEquals(password, login.getPassword(), "getPassword");
    }

    @Test
    void setEmail() {
        String newEmail = "4576942385b7vn34vntgih@gmail.com";
        login.setEmail(newEmail);
        assertEquals(newEmail, login.getEmail(), "setEmail ");
    }

    @Test
    void setPassword() {
        String newPassword = "352832053259mv308vv3vfgvjgod214";
        login.setPassword(newPassword);
        assertEquals(newPassword, login.getPassword(), "setEmail ");
    }

    @AfterAll
    public static void cleanUp() {

    }


}