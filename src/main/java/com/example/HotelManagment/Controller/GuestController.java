package com.example.HotelManagment.Controller;

import com.example.HotelManagment.DTO.GuestDTO;
import com.example.HotelManagment.Services.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/guests")
public class GuestController {

    Logger logger = Logger.getLogger(GuestController.class.getName());

    @Autowired
    private GuestService guestService;

    @PostMapping("/register")
    public ResponseEntity<?> registerGuest(@RequestBody GuestDTO guestDTO) {
        GuestDTO savedGuest = guestService.registerGuest(guestDTO);
        return ResponseEntity.ok(savedGuest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getGuestProfile(@PathVariable int id) {
        Optional<GuestDTO> guestProfile = guestService.getGuestById(id);
        if (guestProfile.isPresent()) {
            return ResponseEntity.ok(guestProfile.get());
        } else {
            return ResponseEntity.ok("No guest found with ID " + id);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateGuestProfile(@RequestBody GuestDTO guestDTO) {
        GuestDTO updatedGuest = guestService.updateGuest(guestDTO);
        return ResponseEntity.ok(updatedGuest);
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestParam int id, @RequestParam String oldPassword, @RequestParam String newPassword) {
        boolean isChanged = guestService.changePassword(id, oldPassword, newPassword);
        return ResponseEntity.ok(isChanged ? "Password updated successfully" : "Password update failed");
    }
}
