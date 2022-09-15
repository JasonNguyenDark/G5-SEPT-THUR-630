package com.example.backend.stuff;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ScheduleRepository extends CrudRepository<Schedule, Integer> {
    Schedule findByEmail(String email);

}