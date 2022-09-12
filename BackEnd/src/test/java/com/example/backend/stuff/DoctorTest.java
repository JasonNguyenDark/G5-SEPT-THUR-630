package com.example.backend.stuff;
import org.junit.jupiter.api.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class DoctorTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private DoctorRepository doctorRepo;

    // I don't believe the controller will use this doctor Repository.
    @Autowired
    private FormController controller;

    @BeforeEach
    void init() {
        Doctor mike = new Doctor();
        mike.setEmail("aaa@gmail.com");
        mike.setName("mike");
        mike.setPassword("1234");

        Login mockLogin = new Login();
        mockLogin.setEmail(mike.getEmail());
        mockLogin.setPassword(mike.getPassword());

    }
    @Test
    void successfulDoctorLogin() {

        // source of mocking https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#features.testing.spring-boot-applications.mocking-beans
        // This section of code gives the functionality
        // given(this.remoteService.getValue()).willReturn("spring");
        // String reverse = this.reverser.getReverseValue(); // Calls injected RemoteService
        // assertThat(reverse).isEqualTo("gnirps");

        //  Arrange: Ensure the doctor has already been added to the database.
        //  done so in the init()
        //        Act: login with the correct email and password.

        String email = "aaa@gmail.com";
        String password = "1234";
        Doctor personA = new Doctor();
        personA.setEmail(email);
        personA.setPassword(password);
        // this mocks the functionality of doctorRepo.
        given(this.doctorRepo.findByEmailAndPassword(email,password)).willReturn(personA);

        Login login = new Login();
        login.setEmail(email);
        login.setPassword(password);

        // Assert: login method returns successful.
        // use the form controller method

        String expectedReturn = "doctor";

        // doctorRepo is injected into controller and mocks its functionality.
        assertEquals(expectedReturn, this.controller.Login(login),
                "Login as doctor" );
    }
    @Test
    void unSuccessfulDoctorLogin() {
        //  Arrange: Ensure the doctor has already been added to the database.
        //  done so in the init()
        //        Act: choose any email and password to fail.

        String email = "aaa@gmail.com";
        String password = "1234";

        // only return null for this particular email
        given(this.doctorRepo.findByEmailAndPassword(email,password)).willReturn(null);

        Login login = new Login();
        login.setEmail(email);
        login.setPassword(password);

        // Assert: login method returns unsuccessful.
        // use the form controller method

        String expectedReturn = "doctor";

        assertNotEquals(expectedReturn, controller.Login(login),
                "Login with the wrong details as doctor" );

    }
    @Test
    void doctorScheduleUpdate() {
        //        Arrange: Doctor user is logged in. Doctor is on the schedule update field.

        //        Act: Method submit update is called.

        String doctorFakeMethod = "ScheduleUpdated";
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
    }

}