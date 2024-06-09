package com.example.HotelManagment.Services;


import com.example.HotelManagment.Model.Guest;
import com.example.HotelManagment.Repo.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GuestService {

    @Autowired
    private GuestRepository guestRepository;

    public List<Guest> findAllGuests() {
        return guestRepository.findAll();
    }

    public Guest findGuestById(int id) {
        return guestRepository.findById(id).orElseGet(null);
    }

    public Guest saveGuest(Guest guest) {
        return guestRepository.save(guest);
    }

    public Guest updateGuest(int id, Guest newGuest) {
        return guestRepository.findById(id)
                .map(guest -> {
                    guest.setFirstName(newGuest.getFirstName());
                    guest.setLastName(newGuest.getLastName());
                    guest.setEmailAddress(newGuest.getEmailAddress());
                    guest.setPhoneNumber(newGuest.getPhoneNumber());
                    return guestRepository.save(guest);
                }).orElseGet(() -> {
                    newGuest.setGuestId(id);
                    return guestRepository.save(newGuest);
                });
    }

    public void deleteGuest(int id) {
        guestRepository.deleteById(id);
    }
}
