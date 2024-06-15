package com.example.HotelManagment.Repo;

import com.example.HotelManagment.Model.Housekeeping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HousekeepingRepository extends JpaRepository<Housekeeping, Integer> {
}
