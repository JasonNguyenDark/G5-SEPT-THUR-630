package com.example.backend.model;
import com.example.backend.model.*;
import org.junit.jupiter.api.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
class DoctorTest {
    private static Doctor doctor;

    @BeforeAll
    public static void initAll() {
        doctor = new Doctor();

    }

    @BeforeEach
    void init() {
        Integer id = 5;
        String name = "Mike";
        String password = "password";
        String email = "Email@gmail.com";
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
        assertEquals(id, doctor.getId());
    }

    @Test
    void setId() {
        Integer newID = 6;
        doctor.setId(newID);
        assertEquals(newID, doctor.getId());

    }

    @Test
    void getName() {
        String name = "Mike";
        assertEquals(name, doctor.getName());
    }

    @Test
    void setName() {
        String newName = "Mikeal";
        doctor.setName(newName);
        assertEquals(newName, doctor.getName());
    }

    @Test
    void getSurname() {
        String surname = "Biege";
        assertEquals(surname, doctor.getSurname());
    }

    @Test
    void setSurname() {
        String newSurname = "Azure";
        doctor.setSurname(newSurname);
        assertEquals(newSurname, doctor.getSurname());
    }

    @Test
    void getGender() {
        String gender = "N/A";
        assertEquals(gender, doctor.getGender());
    }

    @Test
    void setGender() {
        String newGender = "Male";
        doctor.setGender(newGender);
        assertEquals(newGender, doctor.getGender());
    }

    @Test
    void getEmail() {
        String email = "Email@gmail.com";
        assertEquals(email, doctor.getEmail());
    }

    @Test
    void setEmail() {
        String newEmail = "vsdkvjbds@gmail.com";
        doctor.setEmail(newEmail);
        assertEquals(newEmail, doctor.getEmail());
    }

    @Test
    void getPassword() {
        String password = "password";
        assertEquals(password, doctor.getPassword());
    }

    @Test
    void setPassword() {
        String newPassword = "fsdbvkfdnvvsdnj";
        doctor.setPassword(newPassword);
        assertEquals(newPassword, doctor.getPassword());
    }
}