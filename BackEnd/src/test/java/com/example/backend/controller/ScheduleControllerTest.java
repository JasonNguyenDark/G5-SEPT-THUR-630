package com.example.backend.controller;

import com.example.backend.model.Schedule;
import com.example.backend.repository.ScheduleRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class ScheduleControllerTest {

    @MockBean
    private ScheduleRepository scheduleRepository;

    @Autowired
    private ScheduleController scheduleController;

    @BeforeAll
    public static void initAll() {    }

    @BeforeEach
    void init() {

    }

    @Test
    void add() {
        Schedule schedule = new Schedule();
        String email = "436dgff@gmail.com";
        String date = "aaaa";
        String startTime = "bbbb";
        String duration = "cccc";
        Integer id = 1;

        schedule.setEmail(email);
        schedule.setDate(date);
        schedule.setDuration(duration);
        schedule.setStartTime(startTime);
        schedule.setId(id);

        scheduleController.add(schedule);

        // this mocks the functionality of scheduleRepo.
        given(this.scheduleRepository.findByEmail(email)).willReturn(schedule);

        assertEquals(schedule, scheduleRepository.findByEmail(schedule.getEmail()), "Compare emails");

    }

    @Test
    void getSchedule() {
        Schedule schedule = new Schedule();
    }

    @Test
    void getAllSchedule() {
    }

}