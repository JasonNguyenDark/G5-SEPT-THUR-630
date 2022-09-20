package com.example.backend.controller;

import com.example.backend.model.Schedule;
import com.example.backend.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@RequestMapping(path ="/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Schedule> getAllRecords() {
        // This returns a JSON or XML with the users
        return scheduleRepository.findAll();
    }

    @CrossOrigin
    @PostMapping(path="/add")
    public @ResponseBody void getRecord(@RequestBody Schedule schedule) {
        System.out.println(schedule.getStart_Date());
    }
}
