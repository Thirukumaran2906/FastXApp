package com.hexaware.fastx.repository;

import com.hexaware.fastx.entity.Payment;
import com.hexaware.fastx.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findByBooking(Booking booking);
    List<Payment> findByBooking_Id(Long bookingId);


}
