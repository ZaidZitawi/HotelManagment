package com.example.HotelManagment.Controller;


import com.example.HotelManagment.DTO.GuestDTO;
import com.example.HotelManagment.Services.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/guests")
public class GuestController {

    @Autowired
    private GuestService guestService;

    // POST endpoint for guest registration
    @PostMapping("/register")
    public ResponseEntity<?> registerGuest(@RequestBody GuestDTO guestDTO) {
        GuestDTO savedGuest = guestService.registerGuest(guestDTO);
        return ResponseEntity.ok(savedGuest);
    }

    // POST endpoint for guest login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
        String token = guestService.login(email, password);
        return ResponseEntity.ok(token);
    }

    // GET endpoint for viewing guest profile
    @GetMapping("/{id}")
    public ResponseEntity<?> getGuestProfile(@PathVariable int id) {
        Optional<GuestDTO> guestProfile = guestService.getGuestById(id);
        if (guestProfile.isPresent()) {
            return ResponseEntity.ok(guestProfile.get());
        } else {
            return ResponseEntity.ok("No guest found with ID " + id);
        }
    }

    // PUT endpoint for updating guest profile
    @PutMapping("/update")
    public ResponseEntity<?> updateGuestProfile(@RequestBody GuestDTO guestDTO) {
        GuestDTO updatedGuest = guestService.updateGuest(guestDTO);
        return ResponseEntity.ok(updatedGuest);
    }

    // POST endpoint for changing password
    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestParam int id, @RequestParam String oldPassword, @RequestParam String newPassword) {
        boolean isChanged = guestService.changePassword(id, oldPassword, newPassword);
        return ResponseEntity.ok(isChanged ? "Password updated successfully" : "Password update failed");
    }
}
