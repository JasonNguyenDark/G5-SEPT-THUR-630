package com.example.devbranch2;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DevBranch2ApplicationTests {
    @Autowired
    private DemoController controller;

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }
    @Test
    void patientSignUp() {

    }
    @Test
    void loginPatient() {


    }

    @Test
    void loginDoctor() {


    }

    @Test
    void doctorUpdateSchedule() {


    }

    @Test
    void patientUpdateStatus() {


    }


}
