package com.example.backend.controller;

import com.example.backend.model.Prescription;
import com.example.backend.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

// couple assumption a patient can have multiple prescription
// for example: they can be prescribe for something long term(i.e. chronic illness) and something short term(a cold)
@Controller
@RequestMapping(path = "/prescription")
public class PrescriptionController {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @CrossOrigin
    @PostMapping("/add")
    public @ResponseBody void addPrescription(@RequestBody Prescription prescription) {
        prescriptionRepository.save(prescription);
    }

    // still testing. currently unused by front end
    // but will be used when patient view all their prescription(should be able to print more than one or a list)
    @CrossOrigin
    @PostMapping("/get/prescription")
    public @ResponseBody Prescription getPrescription(@RequestBody Prescription prescription) {
        return prescriptionRepository.findByPatientEmail(prescription.getPatientEmail());
    }
}
