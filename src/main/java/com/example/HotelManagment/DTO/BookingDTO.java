package com.example.HotelManagment.DTO;

import lombok.Data;
import lombok.Getter;

import java.util.Date;

public class BookingDTO {
    @Getter
    private int bookingId;
    @Getter
    private int guestId;
    @Getter
    private String guestName;
    @Getter
    private int roomId;
    private Date checkInDate;
    private Date checkOutDate;
    @Getter
    private Integer numberOfAdults;
    @Getter
    private Integer numberOfChildren;
    @Getter
    private Double totalPrice;
    @Getter
    private String paymentStatus;

    // Constructors, getters, and setters
    public BookingDTO() {}

    public BookingDTO(int bookingId, int guestId,String guestName, int roomId, Date checkInDate, Date checkOutDate, Integer numberOfAdults, Integer numberOfChildren, Double totalPrice, String paymentStatus) {
        this.bookingId = bookingId;
        this.guestId = guestId;
        this.guestName=guestName;
        this.roomId = roomId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.numberOfAdults = numberOfAdults;
        this.numberOfChildren = numberOfChildren;
        this.totalPrice = totalPrice;
        this.paymentStatus = paymentStatus;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public Date getCheckInDate() {
        return (Date) checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return (Date) checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public void setNumberOfAdults(Integer numberOfAdults) {
        this.numberOfAdults = numberOfAdults;
    }

    public void setNumberOfChildren(Integer numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }
}
