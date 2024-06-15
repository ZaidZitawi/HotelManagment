package com.example.HotelManagment.Controller;

import com.example.HotelManagment.DTO.RoomDTO;
import com.example.HotelManagment.Services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping("/")
    public ResponseEntity<RoomDTO> addRoom(@RequestBody RoomDTO roomDTO) {
        RoomDTO savedRoom = roomService.saveRoom(roomDTO);
        return ResponseEntity.ok(savedRoom);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRoomDetails(@PathVariable int id) {
        Optional<RoomDTO> room = roomService.getRoomById(id);
        if (room.isPresent()) {
            return ResponseEntity.ok(room.get());
        } else {
            return ResponseEntity.ok("No room found with ID " + id);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRoom(@PathVariable int id, @RequestBody RoomDTO roomDTO) {
        try {
            RoomDTO updatedRoom = roomService.updateRoom(id, roomDTO);
            return ResponseEntity.ok(updatedRoom);
        } catch (RuntimeException e) {
            return ResponseEntity.ok("No room found with ID " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRoom(@PathVariable int id) {
        try {
            roomService.deleteRoom(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.ok("No room found with ID " + id);
        }
    }

    @GetMapping("/availability")
    public ResponseEntity<List<RoomDTO>> checkAvailability() {
        List<RoomDTO> availableRooms = roomService.checkAvailability();
        return ResponseEntity.ok(availableRooms);
    }
}
