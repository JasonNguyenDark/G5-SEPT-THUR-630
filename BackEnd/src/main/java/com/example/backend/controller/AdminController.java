package com.example.backend.controller;

import com.example.backend.model.Doctor;
import com.example.backend.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path ="/admin")
public class AdminController {

    @Autowired
    private DoctorRepository doctorRepository;

    @CrossOrigin
    @PostMapping(path = "/add/doctor")
    public void addDoctor(@RequestBody Doctor doctor) {
        doctorRepository.save(doctor);
    }
}
