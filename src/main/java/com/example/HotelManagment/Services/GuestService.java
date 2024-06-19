package com.example.HotelManagment.Services;

import com.example.HotelManagment.DTO.GuestDTO;
import com.example.HotelManagment.Model.Guest;
import com.example.HotelManagment.Repo.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class GuestService implements UserDetailsService {

    Logger logger = Logger.getLogger(GuestService.class.getName());

    private  GuestRepository guestRepository;
    private  PasswordEncoder passwordEncoder;


    public GuestDTO registerGuest(GuestDTO guestDTO) {
        Guest guest = new Guest();
        updateGuestEntityFromDTO(guest, guestDTO);
        guest.setPassword(passwordEncoder.encode(guestDTO.getPassword()));
        guest = guestRepository.save(guest);
        return convertToDTO(guest);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        logger.info("Attempting to load user by email: '" + email + "'");
        if (email == null || email.isEmpty()) {
            logger.warning("Email is null or empty");
            throw new UsernameNotFoundException("Email is null or empty");
        }
        Guest guest = guestRepository.findByEmailAddress(email)
                .orElseThrow(() -> {
                    logger.warning("Guest not found with email: '" + email + "'");
                    return new UsernameNotFoundException("Guest not found with email: '" + email + "'");
                });
        logger.info("Guest found: " + guest.getEmailAddress());
        return new org.springframework.security.core.userdetails.User(guest.getEmailAddress(), guest.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("USER")));
    }

    public Optional<GuestDTO> getGuestById(int id) {
        Optional<Guest> guest = guestRepository.findById(id);
        return guest.map(this::convertToDTO);
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
        return new GuestDTO(guest.getGuestId(), guest.getFirstName(), guest.getLastName(), guest.getEmailAddress(), guest.getPhoneNumber(), guest.getPassword());
    }
}
