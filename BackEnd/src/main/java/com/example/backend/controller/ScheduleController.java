package com.example.backend.controller;

import com.example.backend.model.Doctor;
import com.example.backend.model.Schedule;
import com.example.backend.model.User;
import com.example.backend.repository.ScheduleRepository;
import com.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

@Controller
@RequestMapping(path ="/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private com.example.backend.repository.DoctorRepository DoctorRepository;

    @Autowired
    private com.example.backend.repository.UserRepository UserRepository;
    // debugging
    @GetMapping(path="/all")
    public @ResponseBody ArrayList<Schedule> getAllSchedule() {
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

    @CrossOrigin
    @PatchMapping (path ="/updateById")
    public @ResponseBody void updateById(@RequestBody Schedule schedule){
        int curId = 0;
        String pemail = schedule.getEmail();
        String pName = UserRepository.findByEmail(pemail).getName() + ' ' + UserRepository.findByEmail(pemail).getSurname();
        //If didnt received id(find id by pass current Schedule) should not be use.
        if (schedule.getId() == null) {
            String email = schedule.getEmail();
            String sDate = schedule.getDate();
            String sTime = schedule.getStartTime();
            ArrayList<Schedule> docSchedules = scheduleRepository.findAll();
            int i = 0;
            int sizeOfSchedule = docSchedules.size();
            while (i < sizeOfSchedule) {
                if (email.equals(docSchedules.get(i).getEmail()) && sDate.equals(docSchedules.get(i).getDate())
                        && sTime.equals(docSchedules.get(i).getStartTime())) {
                    curId = docSchedules.get(i).getId();
                    break;
                }
                ++i;
            }
        }
        //Should contain int id and string pname
        else {
            curId = schedule.getId();
        }
        Schedule updatedSchedule;
        updatedSchedule = scheduleRepository.findById(curId).get();
        System.out.println(updatedSchedule.getId());
        updatedSchedule.setpatientName(pName);
        scheduleRepository.save(updatedSchedule);
    }


    @CrossOrigin
    @GetMapping (path ="/Bookable")
    public @ResponseBody ArrayList<Schedule> validatedSchedule(@RequestBody User user){
        String uEmail = user.getEmail();
        // Code for remove taken slot
        ArrayList<Schedule> docSchedules = scheduleRepository.findAll();
        ArrayList<Schedule> taken = new ArrayList<>();
        int i = 0;
        int sizeOfSchedule = docSchedules.size();
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int year  = localDate.getYear();
        int month = localDate.getMonthValue();
        int day   = localDate.getDayOfMonth();
        while (i < sizeOfSchedule)
        {
            //parse Date string as Localdate
            LocalDate curDate = LocalDate.parse(docSchedules.get(i).getDate());
            //validate if already taken
            if (docSchedules.get(i).getpatientName()!=null) {
                taken.add(docSchedules.get(i));
            }
            //validate if date is before today
            else if (curDate.isBefore(localDate)
            ) {
                taken.add(docSchedules.get(i));
                System.out.println(docSchedules.get(i).getId());

            }
            // repace email with username for frontend readability; frontend will use schedule Id for further operation
            else{
                String username = null;
                String email = docSchedules.get(i).getEmail();
                Doctor doctor = DoctorRepository.findByEmail(email);
                username = doctor.getName() + ' ' + doctor.getSurname();
                docSchedules.get(i).setEmail(username);
            }
            ++i;
        }
        docSchedules.removeAll(taken);
        return docSchedules;
    }

}
