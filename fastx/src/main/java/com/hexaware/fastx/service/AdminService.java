package com.hexaware.fastx.service;

import com.hexaware.fastx.entity.Booking;
import com.hexaware.fastx.entity.User;
import com.hexaware.fastx.entity.Bus;

import java.util.List;

public interface AdminService {

    List<User> getAllUsers();
    void deleteUser(Long userId);

    List<User> getAllOperators();
    void deleteOperator(Long operatorId);

    List<Booking> getAllBookings();

    List<Bus> getAllBuses();
    void deleteBus(Long busId);
}
