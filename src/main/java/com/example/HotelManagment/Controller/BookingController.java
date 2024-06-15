package com.example.HotelManagment.Controller;

import com.example.HotelManagment.DTO.BookingDTO;
import com.example.HotelManagment.Services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservations")
public class BookingController {

    @Autowired
    private BookingService bookingService;


    //زيادة عشان الناس الغانمة
    @GetMapping
    public ResponseEntity<List<BookingDTO>> getAllBookings() {
        List<BookingDTO> bookings = bookingService.findAllBookings();
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookingById(@PathVariable int id) {
        Optional<BookingDTO> booking = bookingService.findBookingById(id);
        if (booking.isPresent()) {
            return ResponseEntity.ok(booking.get());
        } else {
            return ResponseEntity.ok("No booking found with ID " + id);
        }
    }

    @PostMapping
    public ResponseEntity<BookingDTO> createBooking(@RequestBody BookingDTO bookingDTO) {
        BookingDTO savedBooking = bookingService.saveBooking(bookingDTO);
        return ResponseEntity.ok(savedBooking);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingDTO> updateBooking(@PathVariable int id, @RequestBody BookingDTO bookingDTO) {
        BookingDTO updatedBooking = bookingService.updateBooking(id, bookingDTO);
        return ResponseEntity.ok(updatedBooking);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable int id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/search")
    public ResponseEntity<List<BookingDTO>> searchReservations(@RequestBody BookingDTO bookingDTO) {
        List<BookingDTO> bookings = bookingService.searchReservations(bookingDTO);
        return ResponseEntity.ok(bookings);
    }
}
