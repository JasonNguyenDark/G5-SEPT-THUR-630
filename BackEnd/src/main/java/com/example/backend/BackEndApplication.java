package com.example.backend;

import com.example.backend.controller.FormController;
import com.example.backend.controller.RecordController;
import com.example.backend.controller.ScheduleController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = {FormController.class, RecordController.class, ScheduleController.class})
public class BackEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackEndApplication.class, args);
    }


}
