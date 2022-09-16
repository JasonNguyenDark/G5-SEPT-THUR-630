package com.example.backend.stuff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
    public @ResponseBody void add(@RequestBody Schedule schedule) {
        scheduleRepository.save(schedule);
        System.out.println(schedule.getemail());
    }

    @CrossOrigin
    @GetMapping (path="/getSchedule")
    public @ResponseBody List<Schedule> getSchedule(@RequestBody String email) {

        List<Schedule> docSchedules = scheduleRepository.findAll();
        for (int i = 0; i < docSchedules.size(); i++)
        {
            if (docSchedules.get(i).getemail() != email){
                docSchedules.remove(i);
            }
        }
        return docSchedules;
    }




}
