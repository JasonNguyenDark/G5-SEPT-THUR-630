package com.example.backend.stuff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@RequestMapping(path ="/chat")
public class ChatController {

    @Autowired
    private RecordRepository recordRepository;

    @CrossOrigin
    @PostMapping(path="/addRecord")
    public @ResponseBody void Add(@RequestBody Record record) {
        recordRepository.save(record);
    }

    //  for debugging purpose
    @GetMapping(path="/all")
    public @ResponseBody Iterable<Record> getAllRecords() {
        // This returns a JSON or XML with the users
        return recordRepository.findAll();
    }

    @CrossOrigin
    @PostMapping(path="/getRecord")
    public @ResponseBody Record getRecord(@RequestBody Record record) {
        String email = record.getEmail();

        Record userRecord = recordRepository.findByEmail(email);

        System.out.println(userRecord.getEmail());
        return userRecord;
    }
}
