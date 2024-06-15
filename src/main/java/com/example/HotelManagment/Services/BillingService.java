package com.example.HotelManagment.Services;

import com.example.HotelManagment.DTO.BillingDTO;
import com.example.HotelManagment.Model.Billing;
import com.example.HotelManagment.Model.Booking;
import com.example.HotelManagment.Repo.BillingRepository;
import com.example.HotelManagment.Repo.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BillingService {

    @Autowired
    private BillingRepository billingRepository;

    @Autowired
    private BookingRepository bookingRepository;

    // Convert Entity to DTO
    private BillingDTO convertToDTO(Billing billing) {
        return new BillingDTO(billing.getBillingId(), billing.getBooking().getBookingId(), billing.getAmount(), billing.getStatus());
    }

    // Convert DTO to Entity
    private Billing convertToEntity(BillingDTO dto) {
        Booking booking = bookingRepository.findById(dto.getBillingId()).orElseThrow(() -> new RuntimeException("Booking not found"));
        Billing billing = new Billing();
        billing.setBillingId(dto.getBillingId());
        billing.setBooking(booking);
        billing.setAmount(dto.getAmount());
        billing.setStatus(dto.getStatus());
        return billing;
    }

    public BillingDTO saveBilling(BillingDTO dto) {
        Billing billing = convertToEntity(dto);
        billing = billingRepository.save(billing);
        return convertToDTO(billing);
    }

    public Optional<BillingDTO> getBillingById(int id) {
        return billingRepository.findById(id).map(this::convertToDTO);
    }

    public BillingDTO updateBilling(int id, BillingDTO dto) {
        return billingRepository.findById(id)
                .map(billing -> {
                    Booking booking = bookingRepository.findById(dto.getBillingId()).orElseThrow(() -> new RuntimeException("Booking not found"));
                    billing.setBooking(booking);
                    billing.setAmount(dto.getAmount());
                    billing.setStatus(dto.getStatus());
                    return convertToDTO(billingRepository.save(billing));
                }).orElseThrow(() -> new RuntimeException("Billing record not found"));
    }

    public void deleteBilling(int id) {
        billingRepository.deleteById(id);
    }

    public List<BillingDTO> findAllBillings() {
        return billingRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}

