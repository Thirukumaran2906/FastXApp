package com.hexaware.fastx.service;

import com.hexaware.fastx.dtos.BookingRequest;
import com.hexaware.fastx.entity.Booking;

import java.util.List;

public interface BookingService {
    Booking createBooking(BookingRequest request);
    Booking getBookingById(Long id);
    List<Booking> getAllBookings();
    List<Booking> getBookingsByUserId(Long userId);
    void cancelBooking(Long id);
}
