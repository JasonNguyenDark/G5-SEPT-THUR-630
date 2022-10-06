package com.example.backend.controller;

import com.example.backend.model.Doctor;
import com.example.backend.model.Login;
import com.example.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

// validation should already be done by flutter(flutter has in built tools regarding forms).
@Controller
@RequestMapping(path ="/form")
public class FormController {

    @Autowired
    private com.example.backend.repository.UserRepository UserRepository;

    @Autowired
    private com.example.backend.repository.DoctorRepository DoctorRepository;

    @CrossOrigin
    @PostMapping("/signup")
    public @ResponseBody Boolean SignUp(@RequestBody User user) {
        // check if user is already on the repository
        //System.out.println(UserRepository.findByEmail(user.getEmail()));
        if (UserRepository.findByEmail(user.getEmail()) == null) {
            UserRepository.save(user);
            return true;
        }
        return false;
    }


    //  for debugging purpose
    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return UserRepository.findAll();
    }

    //  for debugging purpose
    @GetMapping(path="/allDoc")
    public @ResponseBody Iterable<Doctor> getAllDoctors() {
        // This returns a JSON or XML with the users
        return DoctorRepository.findAll();
    }

    //handle login form, checkEmail() is already performed before this step
    @CrossOrigin
    @PostMapping("/login")
    public @ResponseBody String Login(@RequestBody Login login) {
        String email = login.getEmail();
        String password = login.getPassword();

        User user = UserRepository.findByEmailAndPassword(email, password);
        Doctor doctor = DoctorRepository.findByEmailAndPassword(email,password);

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

        User user = UserRepository.findByEmail(email);
        Doctor doctor = DoctorRepository.findByEmail(email);

        if(user == null && doctor == null) {
            return true; //email doesnt exist in db
        } else {
            return false; //email already exist in db
        }
    }

    @CrossOrigin
    @PostMapping(path = "/getUsername")
    public @ResponseBody String getUsername(@RequestBody Login login) {
        String email = login.getEmail();
        User user = UserRepository.findByEmail(email);
        Doctor doctor = DoctorRepository.findByEmail(email);
//        System.out.println(doctor);
        String username = null;
        try {
            if (user != null) {
                username = user.getName() + ' ' + user.getSurname();
            } else if (doctor != null) {
                username = doctor.getName() + ' ' + doctor.getSurname();
            }
        }
        catch (Exception e){
        }
//        System.out.println(username);
        return username;
    }
}