package Hotel_Management_System;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private int id;
    private String name;
    private String contactInfo;
    private List<Reservation> bookingHistory;

    public Customer(int id, String name, String contactInfo) {
        this.id = id;
        this.name = name;
        this.contactInfo = contactInfo;
        this.bookingHistory = new ArrayList<>();
    }

    public void addReservation(Reservation reservation) {
        bookingHistory.add(reservation);
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public List<Reservation> getBookingHistory() {
        return bookingHistory;
    }
}

