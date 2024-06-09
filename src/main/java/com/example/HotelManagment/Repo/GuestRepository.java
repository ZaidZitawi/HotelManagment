package com.example.HotelManagment.Repo;

import com.example.HotelManagment.Model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface GuestRepository extends JpaRepository<Guest, Integer> {
    // This method declaration tells Spring Data JPA to generate an implementation that looks for a Guest with the given email address
    Optional<Guest> findByEmailAddress(String emailAddress);
}
