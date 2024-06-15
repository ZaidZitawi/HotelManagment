package com.example.HotelManagment.Repo;

import com.example.HotelManagment.Model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Date;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

    @Query("SELECT b FROM Booking b WHERE " +
            "(:guestName IS NULL OR b.guest.firstName = :guestName) AND " +
            "(:guestId IS NULL OR b.guest.guestId = :guestId) AND " +
            "(:checkInDate IS NULL OR b.checkInDate >= :checkInDate) AND " +
            "(:checkOutDate IS NULL OR b.checkOutDate <= :checkOutDate)")
    List<Booking> searchReservations(@Param("guestName") String guestName,
                                     @Param("guestId") Integer guestId,
                                     @Param("checkInDate") Date checkInDate,
                                     @Param("checkOutDate") Date checkOutDate);
}
