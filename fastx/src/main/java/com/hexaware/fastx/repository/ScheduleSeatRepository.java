package com.hexaware.fastx.repository;

import com.hexaware.fastx.entity.Schedule;
import com.hexaware.fastx.entity.ScheduleSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ScheduleSeatRepository extends JpaRepository<ScheduleSeat, Long> {
    List<ScheduleSeat> findBySchedule(Schedule schedule);
}
