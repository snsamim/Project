import java.time.LocalDate;

class Reservation {
    private Room room;
    private Customer customer;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    public Reservation(Room room, Customer customer, LocalDate checkInDate, LocalDate checkOutDate) {
        this.room = room;
        this.customer = customer;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    // Getters
    public Room getRoom() {
        return room;
    }

    public Customer getCustomer() {
        return customer;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }
}

