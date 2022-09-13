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
class UserTest {
    @BeforeAll
    public static void init() {
        System.out.println("Initialization");
        //        Setup database entry for a dummy user.
        //        Maybe should set up a dummy database? Will consult.

    }
    @Test
    void successSignUp() {
        //        Arrange: Ensure patient details are not already on the database.
        //        Act: use sign up method. Add any email and password.
        //        Assert: sign up method returns successful.
        //        After: remove database information as it only for testing.
        fail("Not implemented");
    }
    @Test
    void missingSignUp() {
        //        Arrange: Nothing in particular
        //        Act: signup with missing username and or password.
        //        Assert: sign up method returns unsuccessful.
        //        Method prints the appropriate message for both fields.
        fail("Not implemented");

    }

    @Test
    void registeredSignUp() {
        //        Arrange: Ensure the patient has already been added to the database.
        //        Act: signup with a registered email and any password.
        //        Assert: sign up method returns unsuccessful.
        //        Print that the user is already registered.
        fail("Not implemented");

    }

    @Test
    void successfulUserLogin() {
        //        Arrange: Ensure the patient has already been added to the database.
        //        Act: login with the correct email and password.
        //        Assert: login method returns successful. User should be able to see correct page.
        //        Redo for both patient users and doctor users.
        fail("Not implemented");

    }

    @Test
    void missingInfoLogin() {
        //        Arrange: Nothing
        //        Act: login with email or password missing.
        //        Assert: login method returns unsuccessful.
        //        Appropriate error message should be displayed for both fields.
        fail("Not implemented");
    }

    @Test
    void unregisteredLogin() {
        //        Arrange: Ensure the email is not registered.
        //        Act: enter an email that is not on the database and any password.
        //        Assert: login method returns unsuccessful.
        //        Error message returned should be that the email was not found on the database.
        fail("Not implemented");
    }

    @Test
    void passwordWrongLogin() {
        //        Arrange: Ensure the email is registered.
        //        Act: enter a registered email and any password but the correct password (stored on the database)
        //        Assert: login method returns unsuccessful.
        //        Error message returned should be that the password is incorrect.
        fail("Not implemented");
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

    @AfterAll
    public static void cleanUp() {
        System.out.println("moni");
        //        Remove the dummy user from the database.
    }


}