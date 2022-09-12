package com.example.backend.stuff;

import org.springframework.data.repository.CrudRepository;

public interface ScheduleRepository extends CrudRepository<Schedule,Integer> {
    Schedule findByEmail(String email);

}