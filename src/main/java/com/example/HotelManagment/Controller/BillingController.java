package com.example.HotelManagment.Controller;

import com.example.HotelManagment.DTO.BillingDTO;
import com.example.HotelManagment.Services.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/billing")
public class BillingController {

    @Autowired
    private BillingService billingService;

    @GetMapping("/")
    public List<BillingDTO> getAllBillings() {
        return billingService.findAllBillings();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BillingDTO> getBillingById(@PathVariable int id) {
        Optional<BillingDTO> billingDTO = billingService.getBillingById(id);
        return billingDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public ResponseEntity<BillingDTO> createBilling(@RequestBody BillingDTO billingDTO) {
        BillingDTO savedBilling = billingService.saveBilling(billingDTO);
        return ResponseEntity.ok(savedBilling);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BillingDTO> updateBilling(@PathVariable int id, @RequestBody BillingDTO billingDTO) {
        BillingDTO updatedBilling = billingService.updateBilling(id, billingDTO);
        return ResponseEntity.ok(updatedBilling);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBilling(@PathVariable int id) {
        billingService.deleteBilling(id);
        return ResponseEntity.noContent().build();
    }
}
