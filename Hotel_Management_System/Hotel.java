import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class Hotel {
    private List<Room> rooms = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();
    private List<Reservation> reservations = new ArrayList<>();
    private int customerIdCounter = 1;


    // Customer Handler
    public Customer addCustomer(String name, String contactInfo) {
        Customer customer = new Customer(customerIdCounter++, name, contactInfo);
        customers.add(customer);
        return customer;
    }

    // Reservation Handler
    public boolean bookRoom(Customer customer, int roomNumber, LocalDate checkIn, LocalDate checkOut) {
        Room room = findRoomByNumber(roomNumber);
        if (room != null && isRoomAvailable(roomNumber, checkIn, checkOut)) {
            Reservation reservation = new Reservation(room, customer, checkIn, checkOut);
            reservations.add(reservation);
            customer.addReservation(reservation);
            return true;
        }
        return false;
    }

    public boolean cancelReservation(Reservation reservation) {
        if (reservations.remove(reservation)) {
            reservation.getCustomer().getBookingHistory().remove(reservation);
            return true;
        }
        return false;
    }

    public boolean addRoom(Room room) {
        // rooom existance check
        if (findRoomByNumber(room.getRoomNumber()) != null) {
            return false;
        }
        rooms.add(room);
        return true;
    }

    public boolean isRoomAvailable(int roomNumber, LocalDate checkIn, LocalDate checkOut) {
        for (Reservation reservation : reservations) {
            if (reservation.getRoom().getRoomNumber() == roomNumber &&
                    checkIn.isBefore(reservation.getCheckOutDate()) &&
                    checkOut.isAfter(reservation.getCheckInDate())) {
                return false;
            }
        }
        return true;
    }

    private Room findRoomByNumber(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                return room;
            }
        }
        return null;
    }

    public Customer findCustomerById(int id) {
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                return customer;
            }
        }
        return null;
    }

    public List<Room> getAvailableRooms(LocalDate checkIn, LocalDate checkOut) {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (isRoomAvailable(room.getRoomNumber(), checkIn, checkOut)) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customers);
    }
}
