package com.example.backend.stuff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path ="/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleRepository scheduleRepository;

    // debugging
    @GetMapping(path="/all")
    public @ResponseBody Iterable<Schedule> getAllSchedule() {
        return scheduleRepository.findAll();
    }

    // TODO issue on post, only able to post email data
    @CrossOrigin
    @PostMapping(path="/addSchedule")
    public @ResponseBody void Add(@RequestBody Schedule schedule) {
        scheduleRepository.save(schedule);
    }


    @CrossOrigin
    @PostMapping(path="/getSchedule")
    public @ResponseBody Schedule getSchedule(@RequestBody Schedule schedule) {
        String email = schedule.getEmail();

        Schedule docSchedule = scheduleRepository.findByEmail(email);

        System.out.println(docSchedule.getEmail());
        return docSchedule;
    }


}
