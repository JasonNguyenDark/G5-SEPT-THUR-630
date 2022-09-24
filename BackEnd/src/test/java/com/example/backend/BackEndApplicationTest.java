package com.example.backend;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BackEndApplicationTest {

    @BeforeAll
    public static void init() {
        System.out.println("Initialization");
        //        Setup database entry for a dummy user.
        //        Maybe should set up a dummy database? Will consult.

    }

    @Test
    void main() {
    }

    @AfterAll
    public static void cleanUp() {
        System.out.println("moni");
        //        Remove the dummy user from the database.
    }

}