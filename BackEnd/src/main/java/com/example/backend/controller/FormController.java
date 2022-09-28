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
    public @ResponseBody void SignUp(@RequestBody User user) {
        UserRepository.save(user);
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

    // Edit profile.
    // Reference https://stackoverflow.com/questions/64275792/spring-boot-crud-edit-profile-updating-profile
    // In this editing, retrieve the instance from the database, update the fields and return it back.
    // Reference for uploading photos: https://www.codejava.net/    /spring-boot/spring-boot-file-upload-tutorial
    // TODO: Resolve issue with postmapping and uploading photo.


    //   value = "/updatePerson", consumes = "application/json", produces = "application/json"
    @PostMapping(path = "/editProfile")
    public @ResponseBody User editProfile(@RequestBody User user) throws Exception {

        User userInstance = UserRepository.findByEmail(user.getEmail());
        //System.out.println(user.getEmail());
        //System.out.println(userInstance);

        if (userInstance != null) {
            /*TODO: profile will edit first name, surname, age, gender, Bio, profile picture. */

            userInstance.setName(user.getName());
            userInstance.setSurname(user.getSurname());
            userInstance.setGender(user.getGender());
            userInstance.setAge(user.getAge());
            userInstance.setBio(user.getBio());

            UserRepository.save(userInstance);
            return userInstance;
        }

        // save does not return user successfully.
        return null;
    }
}