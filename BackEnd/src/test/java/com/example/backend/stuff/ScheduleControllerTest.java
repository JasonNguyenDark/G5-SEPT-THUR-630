package com.example.backend.stuff;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class ScheduleControllerTest {

    @MockBean
    private static ScheduleRepository scheduleRepository;

    @Autowired
    private static ScheduleController scheduleController;

    @BeforeAll
    public static void initAll() {
        scheduleController = new ScheduleController();
    }

    @BeforeEach
    void init() {

    }

    @Test
    void add() {
        Schedule schedule = new Schedule();
        String email = "436dgff@gmail.com";
        String endDate = "ffff";
        String startDate = "aaaa";
        String startTime = "bbbb";
        String endTime = "cccc";
        Integer id = 1;

        schedule.setEmail(email);
        schedule.setEnd_Date(endDate);
        schedule.setEnd_Time(endTime);
        schedule.setStart_Date(startDate);
        schedule.setStart_Time(startTime);
        schedule.setId(id);

        scheduleController.Add(schedule);

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