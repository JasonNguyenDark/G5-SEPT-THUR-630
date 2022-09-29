package com.example.backend.repository;

import com.example.backend.model.Appointment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment, Integer> {
    Appointment findByEmail(String email);

    @Override
    ArrayList<Appointment> findAll();
}