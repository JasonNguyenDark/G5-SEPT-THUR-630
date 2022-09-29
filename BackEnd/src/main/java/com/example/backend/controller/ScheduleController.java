package com.example.backend.controller;

import com.example.backend.model.Schedule;
import com.example.backend.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

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
        System.out.println(schedule.getEmail());
    }

    @CrossOrigin
    @PostMapping (path="/getSchedule")
    public @ResponseBody ArrayList<Schedule> getSchedule(@RequestBody Schedule schedule) {
        String email = schedule.getEmail();
        //        System.out.println(email);
        ArrayList<Schedule> docSchedules = scheduleRepository.findAll();

        ArrayList<Schedule> found = new ArrayList<>();
        int i = 0;
        int sizeOfSchedule = docSchedules.size();
        while (i < sizeOfSchedule)
        {
            //            System.out.println(docSchedules.get(i).getemail());
            if (!email.equals(docSchedules.get(i).getEmail())) {
                //                System.out.println(docSchedules.get(i));
                found.add(docSchedules.get(i));
            }
            ++i;
        }
        docSchedules.removeAll(found);
        return docSchedules;
    }

}
