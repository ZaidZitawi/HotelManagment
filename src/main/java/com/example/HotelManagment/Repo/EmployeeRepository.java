package com.example.HotelManagment.Repo;

import com.example.HotelManagment.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {}

