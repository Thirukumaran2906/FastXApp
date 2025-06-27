package com.hexaware.fastx.service.implementation;


import com.hexaware.fastx.entity.Booking;
import com.hexaware.fastx.entity.User;
import com.hexaware.fastx.entity.Bus;
import com.hexaware.fastx.enums.Role;
import com.hexaware.fastx.repository.BookingRepository;
import com.hexaware.fastx.repository.UserRepository;
import com.hexaware.fastx.repository.BusRepository;
import com.hexaware.fastx.service.AdminService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;
    private final BusRepository busRepository;

    public AdminServiceImpl(UserRepository userRepository,
                            BookingRepository bookingRepository,
                            BusRepository busRepository) {
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
        this.busRepository = busRepository;
    }

    // User management
    @Override
    public List<User> getAllUsers() {
        return userRepository.findByRole(Role.PASSENGER);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    // Operator management
    @Override
    public List<User> getAllOperators() {
        return userRepository.findByRole(Role.BUS_OPERATOR);
    }

    @Override
    public void deleteOperator(Long operatorId) {
        userRepository.deleteById(operatorId);
    }

    // Booking management
    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    // Bus management
    @Override
    public List<Bus> getAllBuses() {
        return busRepository.findAll();
    }

    @Override
    public void deleteBus(Long busId) {
        busRepository.deleteById(busId);
    }
}
