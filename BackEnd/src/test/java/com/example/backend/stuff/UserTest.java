package com.example.backend.stuff;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest
class UserTest {

    @BeforeAll
    public static void initAll() {
        System.out.println("Initialization");
    }
    @AfterAll
    public static void cleanUp() {
        System.out.println("moni");
        //        Remove the dummy user from the database.
    }

    @Test
    void getId() {

    }



    @Test
    void setId() {
    }

    @Test
    void getName() {
    }

    @Test
    void setName() {
    }

    @Test
    void getSurname() {
    }

    @Test
    void setSurname() {
    }

    @Test
    void getGender() {
    }

    @Test
    void setGender() {
    }

    @Test
    void getEmail() {
    }

    @Test
    void setEmail() {
    }

    @Test
    void getPassword() {
    }

    @Test
    void setPassword() {
    }
}