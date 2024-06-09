package com.example.HotelManagment.Repo;

import com.example.HotelManagment.Model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest, Long> {}
