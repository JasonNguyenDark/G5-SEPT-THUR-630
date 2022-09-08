package com.example.backend.stuff;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordRepository extends CrudRepository<Record,Integer> {
    Record findByEmail(String email);

//    @Modifying
//    @Query("update Record set")
//    void updateName(@Param("name") String name);
}
