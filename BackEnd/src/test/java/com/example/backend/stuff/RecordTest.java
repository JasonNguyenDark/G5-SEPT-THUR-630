package com.example.backend.stuff;
import org.junit.jupiter.api.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest
class RecordTest {

    public static Record record;

    @BeforeAll
    public static void initAll() {
        record = new Record();
    }
    @BeforeEach
    void init() {

        String allergies = "Nuts";
        String email = "dfskfmsd@gmail.com";
        String gender = "N/A";
        Integer id = 0;
        String status = "Something";
        String name = "Asahi";
        String surname = "Beer";
        record.setAllergies(allergies);
        record.setEmail(email);
        record.setGender(gender);
        record.setId(id);
        record.setStatus(status);
        record.setName(name);
        record.setSurname(surname);
    }
    @AfterAll
    public static void cleanUp() {

    }

    @Test
    void getId() {
        Integer id = 0;
        assertEquals(id, record.getId());
    }

    @Test
    void setId() {

        Integer newId = 142192;
        record.setId(newId);
        assertEquals(newId, record.getId());
    }

    @Test
    void getName() {
        String name = "Asahi";
        assertEquals(name, record.getName());
    }

    @Test
    void setName() {

        String newName = "Double";
        record.setName(newName);
        assertEquals(newName, record.getName());
    }

    @Test
    void getSurname() {
        String surname = "Beer";
        assertEquals(surname, record.getSurname());
    }

    @Test
    void setSurname() {
        String newSurname = "Tap";
        record.setSurname(newSurname);
        assertEquals(newSurname, record.getSurname());
    }

    @Test
    void getGender() {
        String gender = "N/A";
        assertEquals(gender, record.getGender());
    }

    @Test
    void setGender() {

        String newGender = "Female";
        record.setGender(newGender);
        assertEquals(newGender, record.getGender());
    }

    @Test
    void getEmail() {

        String email = "dfskfmsd@gmail.com";
        assertEquals(email, record.getEmail());
    }

    @Test
    void setEmail() {

        String newEmail = "coldplay@gmail.com";
        record.setEmail(newEmail);
        assertEquals(newEmail, record.getEmail());

    }

    @Test
    void getAllergies() {
        String allergies = "Nuts";
        assertEquals(allergies, record.getAllergies());
    }

    @Test
    void setAllergies() {
        String newAllergies = "Pollen";
        record.setAllergies(newAllergies);
        assertEquals(newAllergies, record.getAllergies());

    }

    @Test
    void getStatus() {
        String status = "Something";
        assertEquals(status, record.getStatus());
    }

    @Test
    void setStatus() {
        String newStatus = "Drunk";
        record.setStatus(newStatus);
        assertEquals(newStatus, record.getStatus());

    }
}