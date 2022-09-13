package com.example.backend.stuff;
import org.junit.jupiter.api.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {

    @MockBean
    private DoctorRepository doctorRepo;

    @MockBean
    private UserRepository usrRepo;

    @Autowired
    private FormController controller;

    @BeforeAll
    static void initAll() {
        System.out.println("That's nice");
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

        String email = "vjtgisurndgjkdmnlkv@gmail.com";
        String password = "5731659vbghsrkd932";
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
    @Test
    void successSignUp() {
        //        Arrange: Ensure patient details are not already on the database.
        // The mock database is already empty.
        String email = "mnvbxcmnxcbvmxcvb@gmail.com";
        String password = "pwqru8932rgv34uiwhr832";
        User personA = new User();
        personA.setEmail(email);
        personA.setPassword(password);


        // Act: use sign up method. Add any email and password.

        // Assert: sign up method returns successful.
        controller.SignUp(personA);

        fail("Sign up returns void");
    }
    @Test
    void missingSignUpBlankPassword() {
        //        Arrange: email and blank password
        String email = "fsdnfsdofvnsfse@gmail.com";
        String missingPassword = "";
        User personA = new User();
        personA.setEmail(email);
        personA.setPassword(missingPassword);
        //        Act: signup with missing username and or password.
        //        Assert: sign up method returns unsuccessful.
        //        Method prints the appropriate message for both fields.
        fail("Functionality not implemented.");

    }
    @Test
    void missingSignUpNullPassword() {
        //  Arrange: email and null password
        String email = "fsdnfsdofvnsfse@gmail.com";
        User personA = new User();
        personA.setEmail(email);
        //        Act: signup with missing username and or password.
        //        Assert: sign up method returns unsuccessful.
        //        Method prints the appropriate message for both fields.
        fail("Functionality not implemented.");

    }
    @Test
    void missingSignUpBlankEmail() {
        //  Arrange: blank email and password
        String blankEmail = "";
        String password = "32535235235";
        User personA = new User();
        personA.setEmail(blankEmail);
        personA.setPassword(password);
        //        Act: signup with missing username and or password.
        //        Assert: sign up method returns unsuccessful.
        //        Method prints the appropriate message for both fields.
        fail("Functionality not implemented.");

    }
    @Test
    void missingSignUpNullEmail() {
        //  Arrange: null email and password
        String password = "32535235235";
        User personA = new User();
        personA.setPassword(password);
        //        Act: signup with missing username and or password.
        //        Assert: sign up method returns unsuccessful.
        //        Method prints the appropriate message for both fields.
        fail("Functionality not implemented.");

    }
    @Test
    void registeredSignUp() {
        //        Arrange: Ensure the patient has already been added to the database.
        String email = "fasjfnsfsgsfnvo@gmail.com";
        String password = "852385723095";

        User personA = new User();
        personA.setEmail(email);
        personA.setPassword(password);
        given(usrRepo.findByEmailAndPassword(email, password)).willReturn(personA);

        //        Act: signup with a registered email
        String loginEmail = email;

        //        Assert: sign up method returns unsuccessful.
        controller.SignUp(personA);
        //        Print that the user is already registered.
        fail("Functionality of signup does not allow for this atm.");

    }

    @Test
    void successfulUserLogin() {
        //        Arrange: Ensure the patient has already been added to the database.
        String email = "mnvbxcmnxcbvmxcvb@gmail.com";
        String password = "pwqru8932rgv34uiwhr832";
        User personA = new User();
        personA.setEmail(email);
        personA.setPassword(password);
        // this mocks the functionality of usrRepo method.
        given(this.usrRepo.findByEmailAndPassword(email,password)).willReturn(personA);

        //        Act: login with the correct email and password.

        Login login = new Login();
        login.setEmail(email);
        login.setPassword(password);

        // Assert: login method returns successful.
        // use the form controller method

        String expectedReturn = "user";

        // usrRepo is injected into controller and mocks its functionality.
        assertEquals(expectedReturn, this.controller.Login(login),
                "Login as doctor" );

    }
    @Test
    void signInNullEmail() {
        //        Arrange: password
        //        Act: login with email null.
        String password = "84354305834";
        Login login = new Login();
        login.setPassword(password);

        //        Assert: login method returns null.
        assertNull(controller.Login(login));
        fail("additional information has yet to be implemented");
    }
    @Test
    void signInBlankEmail() {
        //        Arrange: password, blank mail
        //        Act: login with email blank.
        String blankEmail = "";
        String password = "84354305834";
        Login login = new Login();
        login.setEmail(blankEmail);
        login.setPassword(password);

        //        Assert: login method returns null.
        assertNull(controller.Login(login));
        fail("additional information has yet to be implemented");
    }
    @Test
    void signInNullPassword() {
        //        Arrange: email
        //        Act: login with password null.
        String email = "645276r84982674867@gmail.com";
        Login login = new Login();
        login.setEmail(email);

        //        Assert: login method returns unsuccessful.
        assertNull(controller.Login(login));

        fail("additional information has yet to be implemented");
    }
    @Test
    void signInBlankPassword() {
        //        Arrange: email, blank password
        //        Act: login with password blank.
        String email = "645276r84982674867@gmail.com";
        String password = "";
        Login login = new Login();
        login.setEmail(email);
        login.setPassword(password);

        //        Assert: login method returns unsuccessful.
        assertNull(controller.Login(login));

        fail("additional information has yet to be implemented");
    }

    @Test
    void unregisteredLogin() {
        //        Arrange: Ensure the email is not registered. The mock repository is already empty.
        //        Act: enter an email that is not on the database and any password.
        String email = "gvuodrjgovidj@gmail.com";
        String password = "fsefsefsifseifesjfsejfisejfi";
        Login login = new Login();
        login.setEmail(email);
        login.setPassword(password);

        //        Assert: login method returns unsuccessful.
        assertNull(controller.Login(login));
        fail("additional information has yet to be implemented");

    }

    @Test
    void passwordWrongLogin() {
        //        Arrange: Ensure the email is registered.

        //        Act: enter a registered email and any password but the correct password (stored on the database)
        String email = "gvuodrjgovidj@gmail.com";
        String password = "fsefsefsifseifesjfsejfisejfi";

        User personA = new User();
        personA.setEmail(email);
        personA.setPassword(password);
        given(this.usrRepo.findByEmailAndPassword(email, password)).willReturn(personA);

        String fakePassword = "fnsfkisodvfmsdfkisdoefv";
        Login login = new Login();
        login.setEmail(email);
        login.setPassword(fakePassword);

        //        Assert: login method returns unsuccessful.
        //        Error message returned should be that the password is incorrect.
        assertNull(controller.Login(login));
        fail("additional information has yet to be implemented");
    }

    @Test
    void patientStatusUpdate() {
        //        Arrange: Patient user is logged in. Patient is on the status update field.
        //        Act: method submit update is called.

        //        Assert: Method returns successful.
        fail("Not implemented");
    }

    @Test
    void patientCancelStatusUpdate() {
        //        Arrange: Patient user is logged in. Patient is on the status update field.
        //        Act: method cancel update is called.

        //        Assert: Method returns successful.
        fail("Not implemented");
    }
}
