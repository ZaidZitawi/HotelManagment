package com.example.HotelManagment.DTO;

import lombok.Lombok;



public class BillingDTO {
    private int billingId;
    private int reservationId;
    private Double amount;
    private String status;

    public BillingDTO(int billingId, int reservationId, Double amount, String status) {
            this.billingId = billingId;
            this.reservationId = reservationId;
            this.amount = amount;
            this.status = status;
    }

    public int getBillingId() {
        return billingId;
    }

    public void setBillingId(int billingId) {
        this.billingId = billingId;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
