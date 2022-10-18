package com.example.record.Repository;

import com.example.record.Model.Records;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RecordRepository extends JpaRepository<Records, Integer> {
    Records findByEmail(String email);

    @Modifying
    @Query("update Records r set r.status = :status where r.email = :email")
    @Transactional
    void updateStatus(String email, String status);

    @Modifying
    @Query("update Records r set r.allergies = :allergies where r.email = :email")
    @Transactional
    void updateAllergies(String email, String allergies);
}
