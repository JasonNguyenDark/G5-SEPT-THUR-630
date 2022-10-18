package com.example.account.Controller;

import com.example.account.Model.Appointment;
import com.example.account.Model.Doctor;
import com.example.account.Model.Schedule;
import com.example.account.Model.User;
import com.example.account.Repository.AppointmentRepository;
import com.example.account.Repository.DoctorRepository;
import com.example.account.Repository.ScheduleRepository;
import com.example.account.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

@RestController
@RequestMapping(path ="/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository DoctorRepository;

    @Autowired
    private UserRepository UserRepository;
    // debugging
    @GetMapping(path="/all")
    public @ResponseBody ArrayList<Schedule> getAllSchedule() {
        return scheduleRepository.findAll();
    }


    @CrossOrigin
    @PostMapping(path="/addSchedule")
    public @ResponseBody void add(@RequestBody Schedule schedule) {
        scheduleRepository.save(schedule);
    }

    @CrossOrigin
    @PostMapping (path="/getSchedule")
    public @ResponseBody ArrayList<Schedule> getSchedule(@RequestBody Schedule schedule) {
        String email = schedule.getEmail();

        ArrayList<Schedule> docSchedules = scheduleRepository.findAll();

        ArrayList<Schedule> found = new ArrayList<>();

        int i = 0;
        int sizeOfSchedule = docSchedules.size();

        while (i < sizeOfSchedule) {

            if (!email.equals(docSchedules.get(i).getEmail())) {
                found.add(docSchedules.get(i));
            }
            ++i;
        }
        docSchedules.removeAll(found);
        return docSchedules;
    }

    @CrossOrigin
    @PutMapping (path ="/updateById")
    public @ResponseBody void updateById(@RequestBody Schedule schedule) {
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
        updatedSchedule.setpatientName(pName);
        scheduleRepository.save(updatedSchedule);

        Appointment updatedAppointment = new Appointment();
        updatedAppointment.setscheduleId(curId);
        updatedAppointment.setEmail(pemail);
        appointmentRepository.save(updatedAppointment);
    }


    @CrossOrigin
    @PostMapping (path ="/Bookable")
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
        while (i < sizeOfSchedule) {
            //parse Date string as Localdate
            LocalDate curDate = LocalDate.parse(docSchedules.get(i).getDate());
            //validate if already taken
            if (docSchedules.get(i).getpatientName()!=null) {
                taken.add(docSchedules.get(i));
            }
            //validate if date is before today
            else if (curDate.isBefore(localDate)) {
                taken.add(docSchedules.get(i));
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