package com.example.HotelManagment.Services;

import com.example.HotelManagment.Model.Employee;
import com.example.HotelManagment.Repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> findEmployeeById(int id) {
        return employeeRepository.findById(id);
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(int id, Employee newEmployee) {
        return employeeRepository.findById(id)
                .map(employee -> {
                    employee.setFirstName(newEmployee.getFirstName());
                    employee.setLastName(newEmployee.getLastName());
                    employee.setRole(newEmployee.getRole());
                    employee.setEmail(newEmployee.getEmail());
                    employee.setDepartment(newEmployee.getDepartment());
                    return employeeRepository.save(employee);
                }).orElseGet(() -> {
                    newEmployee.setEmployeeId(id);
                    return employeeRepository.save(newEmployee);
                });
    }

    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }
}
