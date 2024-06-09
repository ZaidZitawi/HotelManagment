package com.example.HotelManagment.Services;


import com.example.HotelManagment.Model.Booking;
import com.example.HotelManagment.Repo.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    public BookingRepository bookingRepository;

    public List<Booking> findAllBookings() {
        return bookingRepository.findAll();
    }

    public Optional<Booking> findBookingById(int id) {
        return bookingRepository.findById(id);
    }

    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public Booking updateBooking(int id, Booking newBooking) {
        return bookingRepository.findById(id)
                .map(booking -> {
                    booking.setGuestId(newBooking.getGuestId());
                    booking.setRoomId(newBooking.getRoomId());
                    booking.setCheckInDate(newBooking.getCheckInDate());
                    booking.setCheckOutDate(newBooking.getCheckOutDate());
                    booking.setNumberOfAdults(newBooking.getNumberOfAdults());
                    booking.setNumberOfChildren(newBooking.getNumberOfChildren());
                    booking.setTotalPrice(newBooking.getTotalPrice());
                    booking.setPaymentStatus(newBooking.getPaymentStatus());
                    return bookingRepository.save(booking);
                }).orElseGet(() -> {
                    newBooking.setBookingId(id);
                    return bookingRepository.save(newBooking);
                });
    }

    public void deleteBooking(int id) {
        bookingRepository.deleteById(id);
    }
}
