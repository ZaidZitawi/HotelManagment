package com.example.HotelManagment.Services;

import com.example.HotelManagment.DTO.BookingDTO;
import com.example.HotelManagment.Model.Booking;
import com.example.HotelManagment.Model.Guest;
import com.example.HotelManagment.Model.Room;
import com.example.HotelManagment.Repo.BookingRepository;
import com.example.HotelManagment.Repo.GuestRepository;
import com.example.HotelManagment.Repo.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private RoomRepository roomRepository;

    public List<BookingDTO> findAllBookings() {
        return bookingRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<BookingDTO> findBookingById(int id) {
        return bookingRepository.findById(id).map(this::convertToDTO);
    }

    public BookingDTO saveBooking(BookingDTO bookingDTO) {
        Booking booking = convertToEntity(bookingDTO);
        return convertToDTO(bookingRepository.save(booking));
    }

    public BookingDTO updateBooking(int id, BookingDTO bookingDTO) {
        return bookingRepository.findById(id)
                .map(booking -> {
                    Guest guest = guestRepository.findById(bookingDTO.getGuestId()).orElseThrow(() -> new RuntimeException("Guest not found"));
                    Room room = roomRepository.findById(bookingDTO.getRoomId()).orElseThrow(() -> new RuntimeException("Room not found"));

                    booking.setGuest(guest);
                    booking.setRoom(room);
                    booking.setCheckInDate(bookingDTO.getCheckInDate());
                    booking.setCheckOutDate(bookingDTO.getCheckOutDate());
                    booking.setNumberOfAdults(bookingDTO.getNumberOfAdults());
                    booking.setNumberOfChildren(bookingDTO.getNumberOfChildren());
                    booking.setTotalPrice(bookingDTO.getTotalPrice());
                    booking.setPaymentStatus(bookingDTO.getPaymentStatus());

                    return convertToDTO(bookingRepository.save(booking));
                }).orElseGet(() -> {
                    Booking newBooking = convertToEntity(bookingDTO);
                    newBooking.setBookingId(id);
                    return convertToDTO(bookingRepository.save(newBooking));
                });
    }

    public void deleteBooking(int id) {
        bookingRepository.deleteById(id);
    }

    public List<BookingDTO> searchReservations(BookingDTO bookingDTO) {
        List<Booking> bookings = bookingRepository.searchReservations(
                bookingDTO.getGuestName(),
                bookingDTO.getGuestId(),
                bookingDTO.getCheckInDate(),
                bookingDTO.getCheckOutDate()
        );
        return bookings.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private BookingDTO convertToDTO(Booking booking) {
        return new BookingDTO(
                booking.getBookingId(),
                booking.getGuest().getGuestId(),
                booking.getGuest().getFirstName() + " " + booking.getGuest().getLastName(),
                booking.getRoom().getRoomId(),
                booking.getCheckInDate(),
                booking.getCheckOutDate(),
                booking.getNumberOfAdults(),
                booking.getNumberOfChildren(),
                booking.getTotalPrice(),
                booking.getPaymentStatus()
        );
    }

    private Booking convertToEntity(BookingDTO bookingDTO) {
        Guest guest = guestRepository.findById(bookingDTO.getGuestId()).orElseThrow(() -> new RuntimeException("Guest not found"));
        Room room = roomRepository.findById(bookingDTO.getRoomId()).orElseThrow(() -> new RuntimeException("Room not found"));

        Booking booking = new Booking();
        booking.setBookingId(bookingDTO.getBookingId());
        booking.setGuest(guest);
        booking.setRoom(room);
        booking.setCheckInDate(bookingDTO.getCheckInDate());
        booking.setCheckOutDate(bookingDTO.getCheckOutDate());
        booking.setNumberOfAdults(bookingDTO.getNumberOfAdults());
        booking.setNumberOfChildren(bookingDTO.getNumberOfChildren());
        booking.setTotalPrice(bookingDTO.getTotalPrice());
        booking.setPaymentStatus(bookingDTO.getPaymentStatus());
        return booking;
    }
}
