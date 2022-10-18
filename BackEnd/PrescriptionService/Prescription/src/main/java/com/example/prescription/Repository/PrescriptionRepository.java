package com.example.prescription.Repository;

import com.example.prescription.Model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Integer> {
    Prescription findByPatientEmail(String email);
}
