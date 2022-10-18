package com.example.account.ControllerTests;

import com.example.account.Controller.AdminController;
import com.example.account.Model.Doctor;
import com.example.account.Repository.DoctorRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AdminTests {

    private static Doctor doctor;

    @Autowired
    private AdminController adminController;

    @MockBean
    private DoctorRepository doctorRepository;

    @BeforeAll
    public static void setUp() {
        doctor = new Doctor();
        doctor.setEmail("emily@gmail.com");
        doctor.setName("Emily");
        doctor.setSurname("Baker");
        doctor.setGender("Female");
        doctor.setPassword("abcd1234");
    }


    //admin adding a doctor account
    @Test
    public void successfullyAddedDoctor() {
        adminController.addDoctor(doctor);

        Mockito.when(doctorRepository.findByEmail(doctor.getEmail())).thenReturn(doctor);

        Doctor results = doctorRepository.findByEmail(doctor.getEmail());

        assertEquals(doctor.getEmail(), results.getEmail());
    }
}
