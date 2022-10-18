package com.example.record.Controller;

import com.example.record.Model.Records;
import com.example.record.Repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/record")
public class RecordsController {

    @Autowired
    private RecordRepository recordRepository;

    // add a record
    @CrossOrigin
    @PostMapping(path = "/add")
    public void Add(@RequestBody Records record) {
        recordRepository.save(record);
    }

    //  returns the whole table. use for debugging
    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<Records> getAllRecords() {
        // This returns a JSON or XML with the users
        return recordRepository.findAll();
    }

    // get a single record
    @CrossOrigin
    @PostMapping(path = "/get/record")
    public @ResponseBody
    Records getRecord(@RequestBody Records record) {
        return recordRepository.findByEmail(record.getEmail());
    }

    // update health status/symptoms
    @CrossOrigin
    @PutMapping(path = "/update/status")
    public void updateStatus(@RequestBody Records record) {
        recordRepository.updateStatus(record.getEmail(), record.getStatus());
    }

    // update allergies
    @CrossOrigin
    @PutMapping(path = "/update/allergies")
    public void updateAllergies(@RequestBody Records record) {
        recordRepository.updateAllergies(record.getEmail(), record.getAllergies());
    }
}
