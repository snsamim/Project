package Hotel_Management_System;

import java.time.temporal.ChronoUnit;

class Billing {
    private static final double TAX_RATE = 0.10;

    public static void generateBill(Reservation reservation) {
        long daysStayed = ChronoUnit.DAYS.between(reservation.getCheckInDate(), reservation.getCheckOutDate());
        double roomCost = reservation.getRoom().getPricePerDay() * daysStayed;
        double tax = roomCost * TAX_RATE;
        double total = roomCost + tax;

        System.out.println("\n=== Bill ===");
        System.out.println("Customer: " + reservation.getCustomer().getName());
        System.out.println("Room Number: " + reservation.getRoom().getRoomNumber());
        System.out.println("Room Type: " + reservation.getRoom().getType());
        System.out.println("Check-in Date: " + reservation.getCheckInDate());
        System.out.println("Check-out Date: " + reservation.getCheckOutDate());
        System.out.println("Days Stayed: " + daysStayed);
        System.out.printf("Room Cost: $%.2f\n", roomCost);
        System.out.printf("Tax (10%%): $%.2f\n", tax);
        System.out.printf("Total Amount: $%.2f\n", total);
        System.out.println("============\n");
    }
}