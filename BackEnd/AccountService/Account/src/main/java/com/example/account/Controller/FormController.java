package com.example.account.Controller;


import com.example.account.Model.Admin;
import com.example.account.Model.Doctor;
import com.example.account.Model.Login;
import com.example.account.Model.User;
import com.example.account.Repository.AdminRepository;
import com.example.account.Repository.DoctorRepository;
import com.example.account.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//sign up and login
@RestController
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
    public @ResponseBody
    void SignUp(@RequestBody User user) {
        userRepository.save(user);
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

        System.out.println(password);
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

    @CrossOrigin
    @PostMapping(path = "/getUsername")
    public @ResponseBody String getUsername(@RequestBody Login login) {
        String email = login.getEmail();

        User user = userRepository.findByEmail(email);
        Doctor doctor = doctorRepository.findByEmail(email);
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
