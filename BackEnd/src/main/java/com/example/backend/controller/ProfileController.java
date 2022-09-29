package com.example.backend.controller;

import com.example.backend.model.Doctor;
import com.example.backend.model.User;
import com.example.backend.repository.UserRepository;
import com.example.backend.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@RequestMapping(path ="/profile")
public class ProfileController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @CrossOrigin
    @GetMapping(path = "/get/{role}")
    public @ResponseBody void getProfile(@PathVariable String role) {
        if(role == "profile") {
            return userRepository.findByEmail();
        }
    }

}
