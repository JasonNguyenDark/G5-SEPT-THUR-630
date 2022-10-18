package com.example.account.Repository;


import com.example.account.Model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    Schedule findByEmail(String email);

    @Override
    ArrayList<Schedule> findAll();
}
