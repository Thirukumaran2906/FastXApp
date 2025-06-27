package com.hexaware.fastx.service;

import com.hexaware.fastx.entity.Schedule;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleService {
    Schedule addSchedule(Schedule schedule);
    Schedule getScheduleById(Long id);
    List<Schedule> getAllSchedules();
    void deleteSchedule(Long id);
    List<Schedule> searchSchedules(String origin, String destination, LocalDate journeyDate);

}
