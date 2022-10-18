package com.example.record;

import com.example.record.Controller.RecordsController;
import com.example.record.Model.Records;
import com.example.record.Repository.RecordRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RecordApplicationTests {

    private static Records records;

    @Autowired
    private RecordsController recordsController;

    @MockBean
    private RecordRepository recordRepository;

    @BeforeAll
    public static void setUp() {
        records = new Records();

        records.setEmail("john@gmail.com");
        records.setName("John");
        records.setSurname("Smith");
        records.setGender("Male");
        records.setAllergies("Egg");
        records.setStatus("Fever");
    }

    //test account is successful basically add a account and retrieve it
    @Test
    public void successfullyCreateRecord() {
        //adds a record
        recordsController.Add(records);

        //when findByemail return the expected result
        Mockito.when(recordRepository.findByEmail(records.getEmail())).thenReturn(records);

        //what user input would be
        Records userInput = new Records();

        userInput.setEmail("john@gmail.com");

        //what springboots return
        Records result = recordsController.getRecord(userInput);

        assertEquals(records.getEmail(), result.getEmail());
        assertEquals(records.getName(), result.getName());
        assertEquals(records.getSurname(), result.getSurname());
        assertEquals(records.getGender(), result.getGender());
        assertEquals(records.getAllergies(), result.getAllergies());
        assertEquals(records.getStatus(), result.getStatus());
    }

    @Test
    public void updateAllergies() {
        //adds a record
        recordsController.Add(records);

        Records updatedRecord = new Records();

        updatedRecord.setEmail("john@gmail.com");
        updatedRecord.setName("John");
        updatedRecord.setSurname("Smith");
        updatedRecord.setGender("Male");
        updatedRecord.setAllergies("Nut");
        updatedRecord.setStatus("Fever");

        recordRepository.updateAllergies(updatedRecord.getEmail(), updatedRecord.getAllergies());

        Mockito.when(recordRepository.findByEmail(updatedRecord.getEmail())).thenReturn(updatedRecord);

        recordsController.getRecord(updatedRecord);

        assertNotEquals(records.getAllergies(), updatedRecord.getAllergies());
    }

    @Test
    public void updateStatus() {
        //adds a record
        recordsController.Add(records);

        Records updatedRecord = new Records();

        updatedRecord.setEmail("john@gmail.com");
        updatedRecord.setName("John");
        updatedRecord.setSurname("Smith");
        updatedRecord.setGender("Male");
        updatedRecord.setAllergies("Egg");
        updatedRecord.setStatus("Chronic back pain");

        recordRepository.updateStatus(updatedRecord.getEmail(), updatedRecord.getStatus());

        Mockito.when(recordRepository.findByEmail(updatedRecord.getEmail())).thenReturn(updatedRecord);

        recordsController.getRecord(updatedRecord);

        assertNotEquals(records.getStatus(), updatedRecord.getStatus());
    }
}
