package com.hexaware.fastx.repository;

import com.hexaware.fastx.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByOriginAndDestinationAndJourneyDate(String origin, String destination, LocalDate journeyDate);
}
