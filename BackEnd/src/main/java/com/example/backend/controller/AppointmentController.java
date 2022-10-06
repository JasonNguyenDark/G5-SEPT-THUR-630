package com.example.backend.controller;

import com.example.backend.model.Appointment;
import com.example.backend.model.Schedule;
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
    public @ResponseBody List<Appointment> getAppointment(@RequestBody Appointment appointment) {
        //Will return all appointment match the user email, frontend should check and display the latest
        String email = appointment.getEmail();
        ArrayList<Appointment> userAppointments = appointmentRepository.findAll();
        ArrayList<Appointment> found = new ArrayList<>();
        int i = 0;
        int sizeOfAppointments = userAppointments.size();
        while (i < sizeOfAppointments)
        {
            if (!email.equals(userAppointments.get(i).getEmail())) {
                found.add(userAppointments.get(i));
            }
            ++i;
        }
        userAppointments.removeAll(found);
        return userAppointments;
    }

}
