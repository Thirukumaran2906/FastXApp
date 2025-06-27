package com.hexaware.fastx.service;

import com.hexaware.fastx.entity.Payment;

import java.util.List;

public interface PaymentService {
    Payment makePayment(Payment payment);
    Payment getPaymentById(Long id);
    List<Payment> getPaymentsByBookingId(Long bookingId);
    List<Payment> getAllPayments();
    Payment refundPayment(Long id);

}
