package com.example.account.Repository;

import com.example.account.Model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    Appointment findByEmail(String email);

    @Override
    ArrayList<Appointment> findAll();
}
