package com.example.backend;

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
class BackEndApplicationTests {
    @BeforeAll
    public static void init() {
        System.out.println("Initialization");
        //        Setup database entry for a dummy user.
        //        Maybe should set up a dummy database? Will consult.

    }
    //Commented out as not sure what to do with it atm
    //@Autowired
    //private DemoController controller;
    //@Test
    //public void contextLoads() throws Exception {
    //    assertThat(controller).isNotNull();
    //}



    @AfterAll
    public static void cleanUp() {
        System.out.println("moni");
        //        Remove the dummy user from the database.
    }







}
