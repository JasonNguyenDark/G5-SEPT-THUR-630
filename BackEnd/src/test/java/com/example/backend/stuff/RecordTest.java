package com.example.backend.stuff;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest
class RecordTest {
    @BeforeAll
    void init() {

    }
    @AfterAll
    public static void cleanUp() {
        System.out.println("moni");
        //        Remove the dummy user from the database.
    }

}