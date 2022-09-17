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


    @CrossOrigin
    @PostMapping(path="/addSchedule")
    public @ResponseBody void add(@RequestBody Schedule schedule) {
        scheduleRepository.save(schedule);
        System.out.println(schedule.getemail());
    }

    @CrossOrigin
    @PostMapping (path="/getSchedule")
    public @ResponseBody List<Schedule> getSchedule(@RequestBody Schedule schedule) {
        String email = schedule.getemail();
//        System.out.println(email);
        List<Schedule> docSchedules = scheduleRepository.findAll();
        int i = 0;
        while (i < docSchedules.size())
        {
//            System.out.println(docSchedules.get(i).getemail());
            if (email.equals(docSchedules.get(i).getemail())){
                i++;
            }
            else{
//                System.out.println(docSchedules.get(i));
                docSchedules.remove(i);
            i++;
            }
        }

        return docSchedules;
    }




}
