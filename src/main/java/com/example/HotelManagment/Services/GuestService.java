package com.example.HotelManagment.Services;


import com.example.HotelManagment.DTO.GuestDTO;
import com.example.HotelManagment.Model.Guest;
import com.example.HotelManagment.Repo.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class GuestService {

    @Autowired
    private GuestRepository guestRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public GuestDTO registerGuest(GuestDTO guestDTO) {
        Guest guest = new Guest();
        updateGuestEntityFromDTO(guest, guestDTO);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        guest.setPassword(passwordEncoder.encode(guestDTO.getPassword()));
        guest = guestRepository.save(guest);
        return convertToDTO(guest);
    }

    public String login(String email, String password) {
        Optional<Guest> guestOptional = guestRepository.findByEmailAddress(email);
        if (guestOptional.isPresent()) {
            Guest guest = guestOptional.get();
            if (passwordEncoder.matches(password, guest.getPassword())) {
                return "Logged in successfully";
            } else {
                throw new IllegalArgumentException("Invalid password");
            }
        } else {
            throw new IllegalArgumentException("Guest not found with email: " + email);
        }
    }

    public Optional<GuestDTO> getGuestById(int id) {
        // Try to retrieve the guest from the database
        Optional<Guest> guest = guestRepository.findById(id);

        // If the guest exists, map it to a GuestDTO and return it
        if (guest.isPresent()) {
            GuestDTO guestDTO = convertToDTO(guest.get());
            return Optional.of(guestDTO);
        }

        // If the guest doesn't exist, return an empty Optional
        return Optional.empty();
    }

    public GuestDTO updateGuest(GuestDTO guestDTO) {
        Guest guest = guestRepository.findById(guestDTO.getGuestId())
                .orElseThrow(() -> new IllegalArgumentException("Guest not found with id: " + guestDTO.getGuestId()));
        updateGuestEntityFromDTO(guest, guestDTO);
        guest = guestRepository.save(guest);
        return convertToDTO(guest);
    }

    public boolean changePassword(int id, String oldPassword, String newPassword) {
        Guest guest = guestRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Guest not found with id: " + id));
        if (passwordEncoder.matches(oldPassword, guest.getPassword())) {
            guest.setPassword(passwordEncoder.encode(newPassword));
            guestRepository.save(guest);
            return true;
        } else {
            return false;
        }
    }

    private void updateGuestEntityFromDTO(Guest guest, GuestDTO guestDTO) {
        guest.setFirstName(guestDTO.getFirstName());
        guest.setLastName(guestDTO.getLastName());
        guest.setEmailAddress(guestDTO.getEmailAddress());
        guest.setPhoneNumber(guestDTO.getPhoneNumber());
    }

    private GuestDTO convertToDTO(Guest guest) {
        GuestDTO guestDTO = new GuestDTO(guest.getGuestId(), guest.getFirstName(), guest.getLastName(), guest.getEmailAddress(), guest.getPhoneNumber(), guest.getPassword());
        return guestDTO;
    }
}
