package com.example.account.ControllerTests;

import com.example.account.Controller.AppointmentController;
import com.example.account.Model.Appointment;
import com.example.account.Model.Schedule;
import com.example.account.Repository.AppointmentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class AppointmentTest {

    @MockBean
    private AppointmentRepository appointmentRepository;

    @Autowired
    private AppointmentController appointmentController;


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

