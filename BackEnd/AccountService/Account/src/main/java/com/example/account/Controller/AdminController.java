package com.example.account.Controller;

import com.example.account.Model.Admin;
import com.example.account.Model.Doctor;
import com.example.account.Repository.AdminRepository;
import com.example.account.Repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//atm it is only use to add doctor
//ideally if this project were to be completed to full it would have functions like deleting accounts,
// adding user accounts etc.
@RestController
@RequestMapping(path ="/admin")
public class AdminController {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AdminRepository adminRepository;

    @CrossOrigin
    @PostMapping(path = "/add/doctor")
    public void addDoctor(@RequestBody Doctor doctor) {
        doctorRepository.save(doctor);
    }

    //this is just to make testing easier i.e. adding a admin via postmen rather than logging into aws to do it
    @CrossOrigin
    @PostMapping(path = "/add/admin")
    public void addAdmin(@RequestBody Admin admin) {
        adminRepository.save(admin);
    }

    @CrossOrigin
    @GetMapping(path="/all")
    public @ResponseBody Iterable<Admin> getAllUsers() {
        // This returns a JSON or XML with the users
        return adminRepository.findAll();
    }
}
