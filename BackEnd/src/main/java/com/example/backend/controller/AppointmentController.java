package com.example.backend.controller;

import com.example.backend.model.Appointment;
import com.example.backend.model.Doctor;
import com.example.backend.model.Schedule;
import com.example.backend.repository.AppointmentRepository;
import com.example.backend.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path ="/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private com.example.backend.repository.ScheduleRepository ScheduleRepository;

    @Autowired
    private com.example.backend.repository.DoctorRepository DoctorRepository;
    // debugging
    @GetMapping(path="/all")
    public @ResponseBody Iterable<Appointment> getAllAppointment() {
        return appointmentRepository.findAll();
    }



    @CrossOrigin
    @PostMapping(path="/addAppointment")
    public @ResponseBody void add(@RequestBody Appointment appointment) {
        appointmentRepository.save(appointment);
        System.out.println(appointment.getEmail());
    }

    @CrossOrigin
    @PostMapping (path="/getAppointment")
    public @ResponseBody List<Appointment> getAppointment(@RequestBody Appointment appointment) {
        //Will return all appointment match the user email, frontend should check and display the latest
        String email = appointment.getEmail();
        ArrayList<Appointment> userAppointments = appointmentRepository.findAll();
        ArrayList<Appointment> found = new ArrayList<>();
        int i = 0;
        int sizeOfAppointments = userAppointments.size();
        while (i < sizeOfAppointments)
        {
            if (!email.equals(userAppointments.get(i).getEmail())) {
                found.add(userAppointments.get(i));
            }
            ++i;
        }
        userAppointments.removeAll(found);
        return userAppointments;
    }

    @CrossOrigin
    @PostMapping (path="/getBookedAppointment")
    public @ResponseBody List<Schedule> getBooked(@RequestBody Appointment appointment) {
        //use useemail to first find if theres already booked
        String email = appointment.getEmail();
        ArrayList<Appointment> AllAppointment = appointmentRepository.findAll();
        ArrayList<Appointment> delete = new ArrayList<>();
        ArrayList<Schedule> found = new ArrayList<>();
        int l = 0;
        int sizeOfAppointments = AllAppointment.size();
        while (l < sizeOfAppointments)
        {
            if (!email.equals(AllAppointment.get(l).getEmail())) {
                delete.add(AllAppointment.get(l));
            }
            ++l;
        }
        AllAppointment.removeAll(delete);
        //finish, execute using id to return a list of schedule or return an empty one
        if (AllAppointment.size() > 0){
            ArrayList<Schedule> userBooked = ScheduleRepository.findAll();
            int sizeofAllAppointment = AllAppointment.size();
            int x = 0;
            while (x < sizeofAllAppointment){
                int sId = AllAppointment.get(x).getscheduleId();
                int i = 0;
                int sizeOfSchedules = userBooked.size();
                while (i < sizeOfSchedules)
                {
                    String username = null;
                    String docemail = userBooked.get(i).getEmail();
                    Doctor doctor = DoctorRepository.findByEmail(docemail);
                    username = doctor.getName() + ' ' + doctor.getSurname();
                    userBooked.get(i).setEmail(username);

                    if (userBooked.get(i).getId() != sId) {
                        found.add(userBooked.get(i));
                    }
                    ++i;
                }
                userBooked.removeAll(found);
                ++x;
            }
            return userBooked;
        }
        else{
            return found;
        }
    }


}
