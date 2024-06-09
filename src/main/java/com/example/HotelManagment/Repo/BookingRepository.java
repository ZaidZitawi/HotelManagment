package com.example.HotelManagment.Repo;

import com.example.HotelManagment.Model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Integer> {}

