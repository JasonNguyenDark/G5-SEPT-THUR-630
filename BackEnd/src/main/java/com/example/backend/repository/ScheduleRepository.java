package com.example.backend.repository;

import com.example.backend.model.Schedule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ScheduleRepository extends CrudRepository<Schedule, Integer> {
    Schedule findByEmail(String email);

    @Override
    ArrayList<Schedule> findAll();
}
