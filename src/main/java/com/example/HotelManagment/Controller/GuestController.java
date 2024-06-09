package com.example.HotelManagment.Controller;

import com.example.HotelManagment.Model.Guest;
import com.example.HotelManagment.Services.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guests")
public class GuestController {

    @Autowired
    private GuestService guestService;

    @GetMapping
    public List<Guest> getAllGuests() {
        return guestService.findAllGuests();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Guest> getGuestById(@PathVariable int id) {
        Guest guest = guestService.findGuestById(id);
        if (guest != null) {
            return new ResponseEntity<>(guest, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Guest> createGuest(@RequestBody Guest guest) {
        Guest savedGuest = guestService.saveGuest(guest);
        return new ResponseEntity<>(savedGuest, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Guest> updateGuest(@PathVariable int id, @RequestBody Guest guest) {
        Guest updatedGuest = guestService.updateGuest(id, guest);
        if (updatedGuest != null) {
            return new ResponseEntity<>(updatedGuest, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGuest(@PathVariable int id) {
        guestService.deleteGuest(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
