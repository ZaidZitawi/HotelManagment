package com.example.HotelManagment.Services;

import com.example.HotelManagment.DTO.HousekeepingDTO;
import com.example.HotelManagment.Model.Employee;
import com.example.HotelManagment.Model.Housekeeping;
import com.example.HotelManagment.Model.Room;
import com.example.HotelManagment.Repo.EmployeeRepository;
import com.example.HotelManagment.Repo.HousekeepingRepository;
import com.example.HotelManagment.Repo.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HousekeepingService {

    @Autowired
    private HousekeepingRepository housekeepingRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public HousekeepingDTO createHousekeepingTask(HousekeepingDTO housekeepingDTO) {
        Housekeeping housekeeping = convertToEntity(housekeepingDTO);
        return convertToDTO(housekeepingRepository.save(housekeeping));
    }

    public HousekeepingDTO updateHousekeepingTask(int taskId, HousekeepingDTO housekeepingDTO) {
        return housekeepingRepository.findById(taskId)
                .map(housekeeping -> {
                    Room room = roomRepository.findById(housekeepingDTO.getRoomId()).orElseThrow(() -> new RuntimeException("Room not found"));
                    Employee employee = employeeRepository.findById(housekeepingDTO.getEmployeeId()).orElseThrow(() -> new RuntimeException("Employee not found"));

                    housekeeping.setRoom(room);
                    housekeeping.setEmployee(employee);
                    housekeeping.setTaskDescription(housekeepingDTO.getTaskDescription());
                    housekeeping.setStatus(housekeepingDTO.getStatus());

                    return convertToDTO(housekeepingRepository.save(housekeeping));
                }).orElseThrow(() -> new RuntimeException("Housekeeping task not found"));
    }

    public List<HousekeepingDTO> listAllHousekeepingTasks() {
        return housekeepingRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private HousekeepingDTO convertToDTO(Housekeeping housekeeping) {
        return new HousekeepingDTO(
                housekeeping.getTaskId(),
                housekeeping.getRoom().getRoomId(),
                housekeeping.getEmployee().getEmployeeId(),
                housekeeping.getTaskDescription(),
                housekeeping.getStatus()
        );
    }

    private Housekeeping convertToEntity(HousekeepingDTO housekeepingDTO) {
        Room room = roomRepository.findById(housekeepingDTO.getRoomId()).orElseThrow(() -> new RuntimeException("Room not found"));
        Employee employee = employeeRepository.findById(housekeepingDTO.getEmployeeId()).orElseThrow(() -> new RuntimeException("Employee not found"));

        Housekeeping housekeeping = new Housekeeping();
        housekeeping.setTaskId(housekeepingDTO.getTaskId());
        housekeeping.setRoom(room);
        housekeeping.setEmployee(employee);
        housekeeping.setTaskDescription(housekeepingDTO.getTaskDescription());
        housekeeping.setStatus(housekeepingDTO.getStatus());
        return housekeeping;
    }

    public Optional<HousekeepingDTO> getHousekeepingTaskById(int id) {
        return housekeepingRepository.findById(id).map(this::convertToDTO);
    }
}
