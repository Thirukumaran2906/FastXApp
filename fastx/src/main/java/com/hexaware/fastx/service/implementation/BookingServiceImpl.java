package com.hexaware.fastx.service.implementation;

import com.hexaware.fastx.dtos.BookingRequest;
import com.hexaware.fastx.entity.*;
import com.hexaware.fastx.exception.*;
import com.hexaware.fastx.repository.*;
import com.hexaware.fastx.service.BookingService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;
    private final ScheduleSeatRepository scheduleSeatRepository;
    private final PaymentRepository paymentRepository;

    public BookingServiceImpl(
            BookingRepository bookingRepository,
            UserRepository userRepository,
            ScheduleRepository scheduleRepository,
            ScheduleSeatRepository scheduleSeatRepository,
            PaymentRepository paymentRepository
    ) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.scheduleRepository = scheduleRepository;
        this.scheduleSeatRepository = scheduleSeatRepository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Booking createBooking(BookingRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + request.getUserId()));

        Schedule schedule = scheduleRepository.findById(request.getScheduleId())
                .orElseThrow(() -> new ScheduleNotFoundException("Schedule not found with ID: " + request.getScheduleId()));

        List<ScheduleSeat> selectedSeats = scheduleSeatRepository
                .findBySchedule(schedule)
                .stream()
                .filter(seat -> request.getSeatNumbers().contains(seat.getSeatNumber()))
                .toList();

        for (ScheduleSeat seat : selectedSeats) {
            if (seat.getStatus() != ScheduleSeat.Status.AVAILABLE) {
                throw new SeatAlreadyBookedException("Seat " + seat.getSeatNumber() + " is already booked");
            }
        }

        selectedSeats.forEach(seat -> seat.setStatus(ScheduleSeat.Status.BOOKED));
        scheduleSeatRepository.saveAll(selectedSeats);

        double totalFare = selectedSeats.size() * schedule.getFare();

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setSchedule(schedule);
        booking.setTotalFare(totalFare);
        booking.setCancelled(false);
        Booking savedBooking = bookingRepository.save(booking);

        Payment payment = new Payment();
        payment.setBooking(savedBooking);
        payment.setAmount(totalFare);
        payment.setPaymentDate(LocalDateTime.now());
        payment.setPaymentStatus(Payment.PaymentStatus.PAID);
        paymentRepository.save(payment);

        return savedBooking;
    }

    @Override
    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found with ID: " + id));
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public List<Booking> getBookingsByUserId(Long userId) {
        return bookingRepository.findByUser_UserId(userId);
    }

    @Override
    public void cancelBooking(Long id) {
        Booking booking = getBookingById(id);
        if (booking.isCancelled()) {
            throw new AlreadyCancelledExcepiton("Booking is already cancelled");
        }

        booking.setCancelled(true);
        bookingRepository.save(booking);

        List<Payment> payments = paymentRepository.findByBooking_Id(id);
        for (Payment p : payments) {
            p.setPaymentStatus(Payment.PaymentStatus.REFUNDED);
            paymentRepository.save(p);
        }

        List<ScheduleSeat> allSeats = scheduleSeatRepository.findBySchedule(booking.getSchedule());
        for (ScheduleSeat seat : allSeats) {
            if (seat.getStatus() == ScheduleSeat.Status.BOOKED) {
                seat.setStatus(ScheduleSeat.Status.AVAILABLE);
            }
        }
        scheduleSeatRepository.saveAll(allSeats);
    }
}
