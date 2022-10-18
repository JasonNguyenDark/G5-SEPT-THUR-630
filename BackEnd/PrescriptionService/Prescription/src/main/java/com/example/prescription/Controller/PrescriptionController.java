package com.example.prescription.Controller;

import com.example.prescription.Model.Prescription;
import com.example.prescription.Repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/prescription")
public class PrescriptionController {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @CrossOrigin
    @PostMapping("/add")
    public void addPrescription(@RequestBody Prescription prescription) {
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
