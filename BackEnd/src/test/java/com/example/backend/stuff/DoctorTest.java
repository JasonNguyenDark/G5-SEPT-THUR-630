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
class DoctorTest {
    @BeforeAll
    void init() {

    }
    @Test
    void successfulDoctorLogin() {
        //        Arrange: Ensure the patient has already been added to the database.
        //        Act: login with the correct email and password.
        //        Assert: login method returns successful. User should be able to see correct page.
        //        Redo for both patient users and doctor users.
        fail("Not implemented");

    }

    @Test
    void doctorScheduleUpdate() {
        //        Arrange: Doctor user is logged in. Doctor is on the schedule update field.
        //        Act: Method submit update is called.
        //        Assert: Method returns successful.
        fail("Not implemented");
    }

    @Test
    void doctorCancelScheduleUpdate() {
        //        Arrange: Doctor user is logged in. Doctor is on the schedule update field.
        //        Act: Method cancel update is called.
        //        Assert: Method returns successful.
        fail("Not implemented");
    }
    @AfterAll
    public static void cleanUp() {
        System.out.println("moni");
        //        Remove the dummy user from the database.
    }

}