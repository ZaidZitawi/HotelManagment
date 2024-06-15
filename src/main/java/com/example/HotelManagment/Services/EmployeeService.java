package com.example.HotelManagment.Services;

import com.example.HotelManagment.DTO.EmployeeDTO;
import com.example.HotelManagment.Model.Employee;
import com.example.HotelManagment.Repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    private EmployeeDTO convertToDTO(Employee employee) {
        return new EmployeeDTO(
                employee.getEmployeeId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getRole(),
                employee.getEmail(),
                employee.getDepartment()
        );
    }

    private Employee convertToEntity(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setEmployeeId(employeeDTO.getEmployeeId());
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setRole(employeeDTO.getRole());
        employee.setEmail(employeeDTO.getEmail());
        employee.setDepartment(employeeDTO.getDepartment());
        return employee;
    }

    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        Employee employee = convertToEntity(employeeDTO);
        employee = employeeRepository.save(employee);
        return convertToDTO(employee);
    }

    public Optional<EmployeeDTO> findEmployeeById(int id) {
        return employeeRepository.findById(id).map(this::convertToDTO);
    }

    public Optional<EmployeeDTO> updateEmployee(int id, EmployeeDTO employeeDTO) {
        return employeeRepository.findById(id)
                .map(employee -> {
                    employee.setFirstName(employeeDTO.getFirstName());
                    employee.setLastName(employeeDTO.getLastName());
                    employee.setRole(employeeDTO.getRole());
                    employee.setEmail(employeeDTO.getEmail());
                    employee.setDepartment(employeeDTO.getDepartment());
                    employee = employeeRepository.save(employee);
                    return convertToDTO(employee);
                });
    }

    public boolean deleteEmployee(int id) {
        try {
            employeeRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

