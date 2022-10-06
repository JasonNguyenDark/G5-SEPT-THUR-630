package com.example.backend.repository;

import com.example.backend.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface AdminRepository extends JpaRepository<Admin,Integer> {
    Admin findByEmailAndPassword(String email, String password);
    Admin findByEmail(String email);

    //update methods/query

    // update emails, keep in mind that the new email must be unique
    // i.e. the ne email must not exist in doctor, user and admin table
    @Modifying
    @Query("update Admin a set a.email = :newEmail where a.email = :email")
    @Transactional
    void updateEmail(String email, String newEmail);

    // update gender
    @Modifying
    @Query("update Admin a set a.gender = :gender where a.email = :email")
    @Transactional
    void updateGender(String email, String gender);

    // update first name
    @Modifying
    @Query("update Admin a set a.name = :name where a.email = :email")
    @Transactional
    void updateName(String email, String name);

    // update surname
    @Modifying
    @Query("update Admin a set a.surname = :surname where a.email = :email")
    @Transactional
    void updateSurname(String email, String surname);

    // update password
    @Modifying
    @Query("update Admin a set a.password = :password where a.email = :email")
    @Transactional
    void updatePassword(String email, String password);
}
