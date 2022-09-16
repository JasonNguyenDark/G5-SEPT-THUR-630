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
        String date = "aaaa";
        String startTime = "bbbb";
        String duration = "cccc";
        Integer id = 1;

        schedule.setemail(email);
        schedule.setdate(date);
        schedule.setduration(duration);
        schedule.setstartTime(startTime);
        schedule.setid(id);

        scheduleController.add(schedule);

        assertEquals(schedule, scheduleRepository.findByEmail(schedule.getemail()), "Compare emails");

    }

    @Test
    void getSchedule() {
        Schedule schedule = new Schedule();
    }

    @Test
    void getAllSchedule() {
    }

}