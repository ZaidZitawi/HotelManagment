package com.example.hotelmanagement.repository;

import com.example.hotelmanagement.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("SELECT b FROM Booking b WHERE " +
            "(:guestName IS NULL OR b.guestName = :guestName) AND " +
            "(:guestId IS NULL OR b.guestId = :guestId) AND " +
            "(:checkInDate IS NULL OR b.checkInDate >= :checkInDate) AND " +
            "(:checkOutDate IS NULL OR b.checkOutDate <= :checkOutDate)")
    List<Booking> searchReservations(@Param("guestName") String guestName,
                                     @Param("guestId") Long guestId,
                                     @Param("checkInDate") LocalDate checkInDate,
                                     @Param("checkOutDate") LocalDate checkOutDate);
}
