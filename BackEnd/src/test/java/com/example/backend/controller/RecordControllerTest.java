package com.example.backend.controller;

import com.example.backend.model.Record;
import com.example.backend.model.User;
import com.example.backend.repository.RecordRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RecordControllerTest {

    @MockBean
    private RecordRepository recordRepo;

    @Autowired
    private RecordController recordController ;

    @Autowired
    MockMvc mockMvc;
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
    void getAllRecords() throws Exception {
        ArrayList<Record> records = new ArrayList<>();
        final int ITERABLE = 5;
        for (int i = 0; i < ITERABLE; ++i) {
            Record user = new Record();
            user.setId(i);
            records.add(user);
        }

        final int FIND_ID = 2;
        String email = "@email.com";
        records.get(FIND_ID).setEmail(email);

        Mockito.when( recordRepo.findAll() )
                .thenReturn( records );

        MvcResult result =  mockMvc.perform(get("/record/all"))
                .andExpect(status().isOk())
                .andReturn();

        String resultString = result.getResponse().getContentAsString();
        assertTrue(resultString.contains("\"id\":0"));
        assertTrue(resultString.contains("\"id\":1"));
        assertTrue(resultString.contains("\"id\":2"));
        assertTrue(resultString.contains("\"id\":3"));
        assertTrue(resultString.contains("\"id\":4"));

    }

    @Test
    void getRecord() throws Exception {
        ArrayList<Record> records = new ArrayList<>();
        final int ITERABLE = 5;
        for (int i = 0; i < ITERABLE; ++i) {
            Record user = new Record();
            user.setId(i);
            records.add(user);
        }

        final int FIND_ID = 2;
        String email = "@email.com";
        records.get(FIND_ID).setEmail(email);

        Mockito.when( recordRepo.findByEmail(records.get(FIND_ID).getEmail()) )
                .thenReturn( records.get(FIND_ID) );

        MvcResult result = mockMvc.perform(get("/record/get/record")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":2," +
                                "\"name\":\"Guan\"," +
                                "\"surname\": \"bbb\"," +
                                "\"gender\":\"xxx\"," +
                                "\"email\": \"@email.com\"," +
                                "\"allergies\":\"000\"," +
                                " \"status\": \"status\" }"
                        )
                )
                .andExpect(status().isOk())
                .andReturn();

        String resultString = result.getResponse().getContentAsString();
        assertTrue(resultString.contains("\"id\":2"));
        assertTrue(resultString.contains("\"email\":\"@email.com\""));


    }

    @Test
    void updateStatus() throws Exception {

        mockMvc.perform(put("/record/update/status")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":0," +
                                "\"name\":\"Guan\"," +
                                "\"surname\": \"bbb\"," +
                                "\"gender\":\"xxx\"," +
                                "\"email\": \"@email.com\"," +
                                "\"allergies\":\"000\"," +
                                " \"status\": \"status\" }"
                        )
                )
                .andExpect(status().isOk());

    }

    @Test
    void updateAllergies() throws Exception {
        //        Similar to update status, as we can mock
        //        the search but that does not mean anything.
        mockMvc.perform(put("/record/update/allergies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":0," +
                                "\"name\":\"Guan\"," +
                                "\"surname\": \"bbb\"," +
                                "\"gender\":\"xxx\"," +
                                "\"email\": \"@email.com\"," +
                                "\"allergies\":\"000\"," +
                                " \"status\": \"status\" }"
                        )
                )
                .andExpect(status().isOk());
    }
}