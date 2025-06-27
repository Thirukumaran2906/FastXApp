package com.hexaware.fastx.service.implementation;

import com.hexaware.fastx.entity.Bus;
import com.hexaware.fastx.entity.Schedule;
import com.hexaware.fastx.entity.ScheduleSeat;

import com.hexaware.fastx.repository.BusRepository;
import com.hexaware.fastx.repository.ScheduleRepository;
import com.hexaware.fastx.repository.ScheduleSeatRepository;
import com.hexaware.fastx.service.ScheduleService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final BusRepository busRepository;
    private final ScheduleSeatRepository scheduleSeatRepository;

    public ScheduleServiceImpl(
            ScheduleRepository scheduleRepository,
            BusRepository busRepository,
            ScheduleSeatRepository scheduleSeatRepository) {
        this.scheduleRepository = scheduleRepository;
        this.busRepository = busRepository;
        this.scheduleSeatRepository = scheduleSeatRepository;
    }

    @Override
    public Schedule addSchedule(Schedule schedule) {
        Schedule savedSchedule = scheduleRepository.save(schedule);
        Bus bus = busRepository.getById(schedule.getBus().getBusId());
        System.out.print(bus.getTotalSeats());
        List<ScheduleSeat> seatList = new ArrayList<>();
        for (int i = 1; i <= bus.getTotalSeats(); i++) {
            ScheduleSeat seat = new ScheduleSeat();
            seat.setSeatNumber("S" + i);
            seat.setStatus(ScheduleSeat.Status.AVAILABLE);
            seat.setSchedule(savedSchedule);
            seatList.add(seat);
        }
        scheduleSeatRepository.saveAll(seatList);

        return savedSchedule;
    }

    @Override
    public Schedule getScheduleById(Long id) {
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));
    }

    @Override
    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    @Override
    public void deleteSchedule(Long id) {
        scheduleRepository.deleteById(id);
    }

    @Override
    public List<Schedule> searchSchedules(String origin, String destination, LocalDate journeyDate) {
        return scheduleRepository.findByOriginAndDestinationAndJourneyDate(origin, destination, journeyDate);
    }

}
