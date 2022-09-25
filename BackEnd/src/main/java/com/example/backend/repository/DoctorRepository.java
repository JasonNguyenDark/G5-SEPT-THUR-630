package com.example.backend.repository;

import com.example.backend.model.Doctor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends CrudRepository<Doctor,Integer> {
    Doctor findByEmailAndPassword(String email, String password);
    Doctor findByEmail(String email);
}
