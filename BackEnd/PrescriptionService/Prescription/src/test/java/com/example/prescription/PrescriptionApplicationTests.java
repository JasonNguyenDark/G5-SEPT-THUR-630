package com.example.prescription;

import com.example.prescription.Controller.PrescriptionController;
import com.example.prescription.Model.Prescription;
import com.example.prescription.Repository.PrescriptionRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PrescriptionApplicationTests {

    private static Prescription prescription;

    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private PrescriptionController prescriptionController;

    @MockBean
    private PrescriptionRepository prescriptionRepository;

    @BeforeAll
    public static void setUp() throws ParseException {
        prescription = new Prescription();
        prescription.setDoctorName("Lisa Nguyen");
        prescription.setPatientName("John Smith");
        prescription.setDescription("Has a high fever, dizziness, low body temp");
        prescription.setMedicine("some medicine");

        Date date = dateFormat.parse("2022-10-10");

        prescription.setDate(date);

        prescription.setPatientEmail("john@gmail.com");
    }

    @Test
    public void successfullyCreatePrescription() {
        prescriptionController.addPrescription(prescription);

        Mockito.when(prescriptionRepository.findByPatientEmail(prescription.getPatientEmail())).thenReturn(prescription);

        Prescription userInput = new Prescription();

        userInput.setPatientEmail("john@gmail.com");

        Prescription result = prescriptionController.getPrescription(userInput);

        assertEquals(prescription.getPatientEmail(), result.getPatientEmail());
        assertEquals(prescription.getDoctorName(), result.getDoctorName());
        assertEquals(prescription.getPatientName(), result.getPatientName());
        assertEquals(prescription.getDescription(), result.getDescription());
        assertEquals(prescription.getMedicine(), result.getMedicine());
        assertEquals(prescription.getDate(), result.getDate());
    }
}
