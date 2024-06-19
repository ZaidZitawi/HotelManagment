package com.example.HotelManagment.Controller;

import com.example.HotelManagment.DTO.HousekeepingDTO;
import com.example.HotelManagment.Services.HousekeepingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/housekeeping")
public class HouseKeepingController {

    @Autowired
    private HousekeepingService housekeepingService;

    @GetMapping("/")
    public List<HousekeepingDTO> getAllHousekeepingTasks() {
        return housekeepingService.listAllHousekeepingTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HousekeepingDTO> getHousekeepingTaskById(@PathVariable int id) {
        Optional<HousekeepingDTO> housekeepingDTO = housekeepingService.getHousekeepingTaskById(id);
        return housekeepingDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public ResponseEntity<HousekeepingDTO> createHousekeepingTask(@RequestBody HousekeepingDTO housekeepingDTO) {
        HousekeepingDTO savedHousekeepingTask = housekeepingService.createHousekeepingTask(housekeepingDTO);
        return ResponseEntity.ok(savedHousekeepingTask);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HousekeepingDTO> updateHousekeepingTask(@PathVariable int id, @RequestBody HousekeepingDTO housekeepingDTO) {
        HousekeepingDTO updatedHousekeepingTask = housekeepingService.updateHousekeepingTask(id, housekeepingDTO);
        return ResponseEntity.ok(updatedHousekeepingTask);
    }

}
