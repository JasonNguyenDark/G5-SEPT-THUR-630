package com.example.backend.repository;

import com.example.backend.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface RecordRepository extends JpaRepository<Record,Integer> {
    Record findByEmail(String email);

    @Modifying
    @Query("update Record r set r.status = :status where r.email = :email")
    @Transactional
    void updateStatus(String email, String status);

    @Modifying
    @Query("update Record r set r.allergies = :allergies where r.email = :email")
    @Transactional
    void updateAllergies(String email, String allergies);
}
