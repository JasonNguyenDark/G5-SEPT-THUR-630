package com.example.backend.controller;

import com.example.backend.model.Record;
import com.example.backend.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@RequestMapping(path ="/record")
public class RecordController {

    @Autowired
    private RecordRepository recordRepository;

    @CrossOrigin
    @PostMapping(path="/addRecord")
    public @ResponseBody Boolean Add(@RequestBody Record record) {

        // with this one I don't believe you can add records
        // without a unique ID.
        // at the end of the day, it depends on whether records will
        // have different IDs when they are added.

        // .isEmpty() returns true if findById has no valid record
        // to match the id.
        if (recordRepository.findById(record.getId()).isEmpty()) {
            recordRepository.save(record);
            return true;
        }
        return false;
    }

    //  returns the whole table. use for debugging
    @GetMapping(path="/all")
    public @ResponseBody Iterable<Record> getAllRecords() {
        // This returns a JSON or XML with the users
        return recordRepository.findAll();
    }

    // get a single record
    @CrossOrigin
    @GetMapping(path="/get/record")
    public @ResponseBody Record getRecord(@RequestBody Record record) {
        return recordRepository.findByEmail(record.getEmail());
    }

    // update health status/symptoms
    @CrossOrigin
    @PutMapping(path = "/update/status")
    public @ResponseBody void updateStatus(@RequestBody Record record) {
        recordRepository.updateStatus(record.getEmail(), record.getStatus());
    }

    // update allergies
    @CrossOrigin
    @PutMapping(path = "/update/allergies")
    public @ResponseBody void updateAllergies(@RequestBody Record record) {
        recordRepository.updateAllergies(record.getEmail(), record.getAllergies());
    }
}
