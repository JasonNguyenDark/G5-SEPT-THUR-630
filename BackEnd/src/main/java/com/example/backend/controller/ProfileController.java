package com.example.backend.controller;

import com.example.backend.model.Profile;
import com.example.backend.repository.AdminRepository;
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

    @Autowired
    private AdminRepository adminRepository;

    @CrossOrigin
    @PostMapping(path = "/get")
    public @ResponseBody Object getProfile(@RequestBody Profile profile) {
        return switch (profile.getRole()) {
            case "doctor" -> doctorRepository.findByEmail(profile.getEmail());
            case "admin" -> adminRepository.findByEmail(profile.getEmail());
            case "user" -> userRepository.findByEmail(profile.getEmail());
            default -> null;
        };
    }

    // update methods

    // update email
    @CrossOrigin
    @PutMapping(path = "/update/email")
    public void updateEmail(@RequestBody Profile profile) {
        switch (profile.getRole()) {
            case "doctor" -> doctorRepository.updateEmail(profile.getEmail(), profile.getNewEmail());
            case "admin" -> adminRepository.updateEmail(profile.getEmail(), profile.getNewEmail());
            case "user" -> userRepository.updateEmail(profile.getEmail(), profile.getNewEmail());
        }
    }

    // update first name
    @CrossOrigin
    @PutMapping(path = "/update/name")
    public void updateName(@RequestBody Profile profile) {
        switch (profile.getRole()) {
            case "doctor" -> doctorRepository.updateName(profile.getEmail(), profile.getNewName());
            case "admin" -> adminRepository.updateName(profile.getEmail(), profile.getNewName());
            case "user" -> userRepository.updateName(profile.getEmail(), profile.getNewName());
        }
    }

    // update surname
    @CrossOrigin
    @PutMapping(path = "/update/surname")
    public void updateSurname(@RequestBody Profile profile) {
        switch (profile.getRole()) {
            case "doctor" -> doctorRepository.updateSurname(profile.getEmail(), profile.getNewSurname());
            case "admin" -> adminRepository.updateSurname(profile.getEmail(), profile.getNewSurname());
            case "user" -> userRepository.updateSurname(profile.getEmail(), profile.getNewSurname());
        }
    }

    // update password
    @CrossOrigin
    @PutMapping(path = "/update/password")
    public void updatePassword(@RequestBody Profile profile) {
        switch (profile.getRole()) {
            case "doctor" -> doctorRepository.updatePassword(profile.getEmail(), profile.getNewPassword());
            case "admin" -> adminRepository.updatePassword(profile.getEmail(), profile.getNewPassword());
            case "user" -> userRepository.updatePassword(profile.getEmail(), profile.getNewPassword());
        }
    }
}
