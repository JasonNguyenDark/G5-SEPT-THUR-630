package com.example.backend.controller;

import com.example.backend.model.Appointment;
import com.example.backend.model.Schedule;
import com.example.backend.repository.AppointmentRepository;
import com.example.backend.repository.DoctorRepository;
import com.example.backend.repository.ScheduleRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class AppointmentControllerTest {
    @MockBean
    private ScheduleRepository scheduleRepository;

    @MockBean
    private AppointmentRepository appointmentRepository;

    @MockBean
    private DoctorRepository doctorRepository;

    @Autowired
    private AppointmentController appointmentController;

    @BeforeAll
    public static void initAll() {
    }

    @BeforeEach
    void init() {
    }

    @Test
    void add(){
        Appointment appointment = new Appointment();
        Integer id = 1;
        String email = "example@abcd.com";
        Integer scheduleId = 2;

        appointment.setId(id);
        appointment.setEmail(email);
        appointment.setscheduleId(scheduleId);

        appointmentController.add(appointment);

        given(this.appointmentRepository.findByEmail(email)).willReturn(appointment);

        assertEquals(appointment, appointmentRepository.findByEmail(appointment.getEmail()), "Compare emails");

    }

    @Test
    void getAppointment(){
        Appointment appointment = new Appointment();
        Integer id = 1;
        String email = "example@abcd.com";
        Integer scheduleId = 2;

        appointment.setId(id);
        appointment.setEmail(email);
        appointment.setscheduleId(scheduleId);

        ArrayList<Appointment> appointmentArrayList = new ArrayList<>();

        given(appointmentRepository.findAll()).willReturn(appointmentArrayList);
        appointmentArrayList.add(appointment);
        Integer dummy_size = appointmentArrayList.size();
        ArrayList<Appointment> dummy_list = new ArrayList<>();
        Appointment dummy = new Appointment();
        dummy_list.add(dummy);
        given(appointmentController.getAppointment(appointment)).willReturn(dummy_list);
        assertEquals(dummy_list.size(), dummy_size);
    }

    @Test
    void getBooked(){
        Appointment appointment = new Appointment();
        Integer id = 1;
        String email = "example@abcd.com";
        Integer scheduleId = 2;

        appointment.setId(id);
        appointment.setEmail(email);
        appointment.setscheduleId(scheduleId);
        ArrayList<Schedule> empty_list = new ArrayList();
        given(appointmentController.getBooked(appointment)).willReturn(empty_list);
        assertEquals(empty_list.size(), 0);
    }
}
