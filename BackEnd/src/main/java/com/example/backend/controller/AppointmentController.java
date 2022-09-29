package com.example.backend.controller;

import com.example.backend.model.Appointment;
import com.example.backend.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path ="/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentRepository appointmentRepository;

    // debugging
    @GetMapping(path="/all")
    public @ResponseBody Iterable<Appointment> getAllAppointment() {
        return appointmentRepository.findAll();
    }


    @CrossOrigin
    @PostMapping(path="/addAppointment")
    public @ResponseBody void add(@RequestBody Appointment appointment) {
        appointmentRepository.save(appointment);
        System.out.println(appointment.getEmail());
    }

    @CrossOrigin
    @PostMapping (path="/getAppointment")
    public @ResponseBody List<Integer> getAppointment(@RequestBody Appointment appointment) {
        String email = appointment.getEmail();
        //        System.out.println(email);
        List<Appointment> userAppointments = appointmentRepository.findAll();
        List<Integer> userScheduleIds = null;
        int i = 0;
        while (i < userAppointments.size())
        {
            if (email.equals(userAppointments.get(i).getEmail())){
                userScheduleIds.add(userAppointments.get(i).getscheduleId());
                i++;
            }
            else{

                userAppointments.remove(i);
                i++;
            }
        }
        return userScheduleIds;
    }

}
