package com.example.HotelManagment.Security;

import com.example.HotelManagment.Model.Guest;
import com.example.HotelManagment.Repo.GuestRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.logging.Logger;
import com.example.HotelManagment.Exceptions.UserNotFoundException;
@Service
public class CustomUserDetailsService implements UserDetailsService {

    Logger logger = Logger.getLogger(CustomUserDetailsService.class.getName());

    private final GuestRepository guestRepository;

    public CustomUserDetailsService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UserNotFoundException {
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
        return new org.springframework.security.core.userdetails.User(
                guest.getEmailAddress(),
                guest.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("USER"))
        );
    }
}
