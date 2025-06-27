package com.hexaware.fastx.service.implementation;

import com.hexaware.fastx.entity.Payment;
import com.hexaware.fastx.exception.PaymentNotFoundException;
import com.hexaware.fastx.repository.PaymentRepository;
import com.hexaware.fastx.service.PaymentService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment makePayment(Payment payment) {
        payment.setPaymentDate(LocalDateTime.now());
        payment.setPaymentStatus(Payment.PaymentStatus.PAID); // default
        return paymentRepository.save(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found with ID: " + id));
    }

    @Override
    public List<Payment> getPaymentsByBookingId(Long bookingId) {
        return paymentRepository.findByBooking_Id(bookingId);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment refundPayment(Long id) {
        Payment payment = getPaymentById(id);
        payment.setPaymentStatus(Payment.PaymentStatus.REFUNDED);
        return paymentRepository.save(payment);
    }
}
