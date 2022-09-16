package com.example.backend.stuff;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends CrudRepository<Schedule, Integer> {
    Schedule findByEmail(String email);

    @Override
    List<Schedule> findAll();
}