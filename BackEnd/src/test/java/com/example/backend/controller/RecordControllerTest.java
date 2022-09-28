package com.example.backend.controller;

import com.example.backend.repository.RecordRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.example.backend.model.Record;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class RecordControllerTest {

    @MockBean
    private RecordRepository recordRepo;

    @Autowired
    private RecordController recordController ;
    @Test
    void add() {
        Record record = new Record();
        record.setId(0);

        assertTrue(recordController.Add(record));
    }

    @Test
    void addFail() {
        Record record = new Record();
        record.setId(0);

        // default is to give Optional.of, though it is confusing why
        // it is so.
        given(recordRepo.findById(record.getId())).willReturn(Optional.of(record));

        assertFalse(recordController.Add(record));
    }

    @Test
    void getAllRecords() {
    }

    @Test
    void getRecord() {
    }

    @Test
    void updateStatus() {
    }
    
    // invalid test
    //@Test
    //void patientCancelStatusUpdate() {
    //    //        Arrange: Patient user is logged in. Patient is on the status update field.
    //    //        Act: method cancel update is called.
    //
    //    //        Assert: Method returns successful.
    //    fail("Functionality for user status is not implemented.");
    //
    //}

    @Test
    void updateAllergies() {
    }
}