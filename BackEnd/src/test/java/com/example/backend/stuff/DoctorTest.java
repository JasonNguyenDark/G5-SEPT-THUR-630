package com.example.backend.stuff;
import org.junit.jupiter.api.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
class DoctorTest {
    private Doctor doctor;
    @BeforeEach
    void init() {
        doctor = new Doctor();
        Integer id = 5;
        String name = "Mike";
        String password = "password";
        String email = "Email";
        String surname = "Biege";
        String gender = "N/A";

        doctor.setId(id);
        doctor.setName(name);
        doctor.setPassword(password);
        doctor.setEmail(email);
        doctor.setSurname(surname);
        doctor.setGender(gender);

    }

    @AfterAll
    public static void cleanUp() {
    }

    @Test
    void getId() {
        Integer id = 5;
        doctor.getId();
    }

    @Test
    void setId() {
        Integer newID = 6;

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