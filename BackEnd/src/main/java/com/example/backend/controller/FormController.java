package com.example.backend.controller;

import com.example.backend.model.Doctor;
import com.example.backend.model.Login;
import com.example.backend.model.User;
import com.example.backend.repository.UserRepository;
import com.example.backend.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

// validation should already be done by flutter(flutter has in built tools regarding forms).
@Controller
@RequestMapping(path ="/form")
public class FormController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @CrossOrigin
    @PostMapping("/signup")
    public @ResponseBody void SignUp(@RequestBody User user) {
        userRepository.save(user);
    }


    //  for debugging purpose
    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }

    //  for debugging purpose
    @GetMapping(path="/allDoc")
    public @ResponseBody Iterable<Doctor> getAllDoctors() {
        // This returns a JSON or XML with the users
        return doctorRepository.findAll();
    }

    //handle login form, checkEmail() is already performed before this step
    @CrossOrigin
    @PostMapping("/login")
    public @ResponseBody String Login(@RequestBody Login login) {
        String email = login.getEmail();
        String password = login.getPassword();

        User user = userRepository.findByEmailAndPassword(email, password);
        Doctor doctor = doctorRepository.findByEmailAndPassword(email,password);

        if (user != null && doctor == null) {
            return "user";
        } else if (doctor != null && user == null) {
            return "doctor";
        } else {
            return null;
        }
    }


    // Check if email already exist within the database
    @CrossOrigin
    @PostMapping(path = "/checkEmail")
    public @ResponseBody Boolean checkEmail(@RequestBody Login login) {
        String email = login.getEmail();

        User user = userRepository.findByEmail(email);
        Doctor doctor = doctorRepository.findByEmail(email);

        if(user == null && doctor == null) {
            return true; //email doesnt exist in db
        } else {
            return false; //email already exist in db
        }
    }
}