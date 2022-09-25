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
    public @ResponseBody void Add(@RequestBody Record record) {
        recordRepository.save(record);
    }

    //  returns the whole table. use for debugging
    @GetMapping(path="/all")
    public @ResponseBody Iterable<Record> getAllRecords() {
        // This returns a JSON or XML with the users
        return recordRepository.findAll();
    }

    // get a single record
    @CrossOrigin
    @PostMapping(path="/get/record")
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
