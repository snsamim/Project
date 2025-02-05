package Hotel_Management_System;

// Abstract Room class using inheritance
abstract class Room {
    private int roomNumber;
    private String type;
    private double pricePerDay;

    public Room(int roomNumber, String type, double pricePerDay) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.pricePerDay = pricePerDay;
    }

    // Getters
    public int getRoomNumber() {
        return roomNumber;
    }

    public String getType() {
        return type;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    static class StandardRoom extends Room {
        public StandardRoom(int roomNumber, double pricePerDay) {
            super(roomNumber, "Standard", pricePerDay);
        }
    }

    
    static class DeluxeRoom extends Room {
        public DeluxeRoom(int roomNumber, double pricePerDay) {
            super(roomNumber, "Deluxe", pricePerDay);
        }
    }
}


