package com.example.backend.repository;

import com.example.backend.model.Record;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordRepository extends CrudRepository<Record,Integer> {
    Record findByEmail(String email);

//    @Modifying
//    @Query("update Record set")
//    void updateName(@Param("name") String name);
}
