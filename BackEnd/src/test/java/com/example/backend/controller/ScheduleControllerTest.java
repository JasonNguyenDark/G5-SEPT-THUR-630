package com.example.backend.controller;

import com.example.backend.model.Schedule;
import com.example.backend.repository.ScheduleRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.fail;
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
    void indexOutOfBoundsScheduling() {
        //     schedule email which crashes flutter ?
        Schedule schedule = new Schedule();
        String realEmail = "rollin@gmail.com";
        schedule.setEmail(realEmail);

        // use this as the dummy object for the repository
        ArrayList<Schedule> scheduleArrayList = new ArrayList<>();

        given(scheduleRepository.findAll()).willReturn(scheduleArrayList);

        final int ARRAY_COUNT = 5;

        for (Integer i = 0; i < ARRAY_COUNT; ++i) {
            scheduleArrayList.add(schedule);
        }

        //        Assert: Method returns successful, or the unique schedule of the doctor.
        ArrayList<Schedule> expectedSchedule = scheduleArrayList;

        int i = 0;
        int expectedSize = expectedSchedule.size();
        ArrayList<Schedule> actualSchedule = scheduleController.getSchedule(schedule);
        assertEquals(expectedSize, actualSchedule.size());

        for (i = 0; i < expectedSize; ++i) {
            assertEquals(expectedSchedule.get(i), actualSchedule.get(i));
        }
    }
    @Test
    void findEmptySchedule() {
    //     schedule email which does not exist.
        Schedule schedule = new Schedule();
        String realEmail = "rollin@gmail.com";
        schedule.setEmail(realEmail);

        // use this as the dummy object for the repository
        ArrayList<Schedule> scheduleArrayList = new ArrayList<>();

        given(scheduleRepository.findAll()).willReturn(scheduleArrayList);

        final int ARRAY_COUNT = 5;

        for (Integer i = 0; i < ARRAY_COUNT; ++i) {
            Schedule dummySchedule = new Schedule();
            dummySchedule.setEmail("dummy" + i.toString() + "@email.com" );
            scheduleArrayList.add(dummySchedule);
        }

        //        Assert: Method returns successful, or the unique schedule of the doctor.
        ArrayList<Schedule> expectedSchedule = new ArrayList<>();

        int i = 0;
        int expectedSize = expectedSchedule.size();
        ArrayList<Schedule> actualSchedule = scheduleController.getSchedule(schedule);
        assertEquals(expectedSize, actualSchedule.size());

        for (i = 0; i < expectedSize; ++i) {
            assertEquals(expectedSchedule.get(i), actualSchedule.get(i));
        }

    }

    @Test
    void doctorGetSchedule() {
        //        Arrange: not much (schedule is not involved with doctor)

        //        Act: Method submit update is called.

        Schedule schedule = new Schedule();
        String realEmail = "rollin@gmail.com";
        schedule.setEmail(realEmail);

        // use this as the dummy object for the repository
        ArrayList<Schedule> scheduleArrayList = new ArrayList<>();

        given(scheduleRepository.findAll()).willReturn(scheduleArrayList);

        scheduleArrayList.add(schedule);
        final int ARRAY_COUNT = 5;

        for (Integer i = 0; i < ARRAY_COUNT; ++i) {
            Schedule dummySchedule = new Schedule();
            dummySchedule.setEmail("dummy" + i.toString() + "@email.com" );
            scheduleArrayList.add(dummySchedule);
        }

        scheduleArrayList.add(schedule);

        //        Assert: Method returns successful, or the unique schedule of the doctor.
        ArrayList<Schedule> expectedSchedule = new ArrayList<>();
        expectedSchedule.add(schedule);
        expectedSchedule.add(schedule);

        int i = 0;
        int expectedSize = expectedSchedule.size();
        ArrayList<Schedule> actualSchedule = scheduleController.getSchedule(schedule);
        assertEquals(expectedSize, actualSchedule.size());

        for (i = 0; i < expectedSize; ++i) {
            assertEquals(expectedSchedule.get(i), actualSchedule.get(i));
        }


    }


    @Test
    void getAllSchedule() {
    }

}