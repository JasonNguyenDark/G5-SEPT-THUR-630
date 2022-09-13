package com.example.backend.stuff;

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
    ScheduleRepository scheduleRepository;

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

        schedule.setemail(email);
        schedule.setend_date(endDate);
        schedule.setend_time(endTime);
        schedule.setstart_date(startDate);
        schedule.setstart_time(startTime);
        schedule.setid(id);


        scheduleController.add(schedule);

        assertEquals(schedule, scheduleRepository.findByEmail(schedule.getemail()), "Compare emails");

    }

    @Test
    void getSchedule() {

        Schedule schedule = new Schedule();
        String email = "schedule@gmail.com";
        schedule.setemail(email);

        given(scheduleRepository.findByEmail(email)).willReturn(schedule);
        assertEquals(schedule, scheduleController.getSchedule(schedule));
    }

    @Test
    void getAllSchedule() {
    }

}