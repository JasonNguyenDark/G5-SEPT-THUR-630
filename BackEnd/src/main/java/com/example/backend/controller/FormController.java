package com.example.backend.controller;

import com.example.backend.model.Admin;
import com.example.backend.model.Doctor;
import com.example.backend.model.Login;
import com.example.backend.model.User;
import com.example.backend.repository.AdminRepository;
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

    @Autowired
    private AdminRepository adminRepository;

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
        Admin admin = adminRepository.findByEmailAndPassword(email, password);

        // although it can be simplify like below
        // if(user != null) { code }
        // the other 2 null check are used in the case where
        // a admin, or someone with direct access to db, accidentally add an account using same email
        // e.g. abcd@gmail.com is in user table and doctor table
        if (user != null && doctor == null && admin == null) {
            return "user";
        } else if (doctor != null && user == null && admin == null) {
            return "doctor";
        } else if (admin != null && user == null && doctor == null) {
            return "admin";
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
        Admin admin = adminRepository.findByEmail(email);

        if(user == null && doctor == null && admin == null) {
            return true; //email doesnt exist in db
        } else {
            return false; //email already exist in db
        }
    }
}