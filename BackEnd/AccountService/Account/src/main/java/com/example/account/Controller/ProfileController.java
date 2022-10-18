package com.example.account.Controller;

import com.example.account.Model.Profile;
import com.example.account.Repository.AdminRepository;
import com.example.account.Repository.DoctorRepository;
import com.example.account.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/profile")
public class ProfileController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AdminRepository adminRepository;

    // retrieve profile, due to how front end is coded null should not be possible(null in this method is more like a fail safe)
    @CrossOrigin
    @PostMapping(path = "/get")
    public @ResponseBody
    Object getProfile(@RequestBody Profile profile) {
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

    // update gender
    @CrossOrigin
    @PutMapping(path = "/update/gender")
    public void updateGender(@RequestBody Profile profile) {
        switch (profile.getRole()) {
            case "doctor" -> doctorRepository.updateGender(profile.getEmail(), profile.getNewGender());
            case "admin" -> adminRepository.updateGender(profile.getEmail(), profile.getNewGender());
            case "user" -> userRepository.updateGender(profile.getEmail(), profile.getNewGender());
        }
    }
}
