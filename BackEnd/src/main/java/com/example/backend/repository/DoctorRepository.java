package com.example.backend.repository;

import com.example.backend.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Integer> {
    Doctor findByEmailAndPassword(String email, String password);
    Doctor findByEmail(String email);

    //update methods/query

    // update emails, keep in mind that the new email must be unique
    // i.e. the ne email m ust not exist in doctor, user and admin table
    @Modifying
    @Query("update Doctor d set d.email = :newEmail where d.email = :email")
    @Transactional
    void updateEmail(String email, String newEmail);

    // update gender
    @Modifying
    @Query("update Doctor d set d.gender = :gender where d.email = :email")
    @Transactional
    void updateGender(String email, String gender);

    // update first name
    @Modifying
    @Query("update Doctor d set d.name = :name where d.email = :email")
    @Transactional
    void updateName(String email, String name);

    // update surname
    @Modifying
    @Query("update Doctor d set d.surname = :surname where d.email = :email")
    @Transactional
    void updateSurname(String email, String surname);

    // update password
    @Modifying
    @Query("update Doctor d set d.password = :password where d.email = :email")
    @Transactional
    void updatePassword(String email, String password);
}
