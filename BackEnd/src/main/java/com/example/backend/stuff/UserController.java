package com.example.backend.stuff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/#")
public class UserController {

    @Autowired
    private UserJPARepository UserJPARepository;

    @PostMapping("/signUp")
    public User SignUp(@RequestBody User user) {
        System.out.println("hello");
        return UserJPARepository.save(user);
    }

//    @PostMapping("/login")
//    public User Login(@RequestBody User user) {
//        User account = UserJPARepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
//        System.out.println(account); //debugger
//        return account;
//    }
}
