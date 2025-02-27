import java.time.LocalDate;
import java.util.*;

public class HotelManagementSystem {
    private static Hotel hotel = new Hotel();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeSampleData();

        while (true) {
            System.out.println("\nHotel Management System");
            System.out.println("1. Add Room");
            System.out.println("2. Add Customer");
            System.out.println("3. Book Room");
            System.out.println("4. Cancel Reservation");
            System.out.println("5. Check Room Availability");
            System.out.println("6. Generate Bill");
            System.out.println("7. View Customer Booking History");
            System.out.println("8. View All Customers");
            System.out.println("9. Exit");
            System.out.print("Select option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addRoom();
                    break;
                case 2:
                    addCustomer();
                    break;
                case 3:
                    bookRoom();
                    break;
                case 4:
                    cancelReservation();
                    break;
                case 5:
                    checkAvailability();
                    break;
                case 6:
                    generateBill();
                    break;
                case 7:
                    viewBookingHistory();
                    break;
                case 8:
                    viewAllCustomers();
                    break;
                case 9:
                    System.out.println("Exiting system...");
                    return;
            }
        }
    }

    private static void initializeSampleData() {
        hotel.addRoom(new Room.DeluxeRoom(101, 200));
        hotel.addRoom(new Room.StandardRoom(201, 100));
    }

    private static void addRoom() {
        System.out.print("Enter room number: ");
        int number = scanner.nextInt();
        System.out.print("Enter room type (1. Deluxe 2. Standard): ");
        int typeChoice = scanner.nextInt();
        System.out.print("Enter daily rate: ");
        double rate = scanner.nextDouble();
        scanner.nextLine();

        Room room = typeChoice == 1 ? new Room.DeluxeRoom(number, rate) : new Room.StandardRoom(number, rate);

        if (hotel.addRoom(room)) {
            System.out.println("Room added successfully!");
        } else {
            System.out.println("Error: Room number " + number + " already exists!");
        }
    }


    private static void addCustomer() {
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();
        System.out.print("Enter contact info: ");
        String contact = scanner.nextLine();

        Customer newCustomer = hotel.addCustomer(name, contact);
        System.out.println("Customer added successfully!");
        System.out.println("Your customer ID is: " + newCustomer.getId());
    }

    private static void viewAllCustomers() {
        List<Customer> customers = hotel.getAllCustomers();
        if (customers.isEmpty()) {
            System.out.println("\nNo customers found!");
            return;
        }

        System.out.println("\n=== All Customers ===");
        System.out.printf("%-8s %-20s %-15s\n", "ID", "Name", "Contact Info");
        for (Customer customer : customers) {
            System.out.printf("%-8d %-20s %-15s\n",
                    customer.getId(), customer.getName(), customer.getContactInfo());
        }
        System.out.println("======================");
    }

    private static void bookRoom() {
        System.out.print("Enter customer ID: ");
        int customerId = scanner.nextInt();
        Customer customer = hotel.findCustomerById(customerId);
        if (customer == null) {
            System.out.println("Customer not found!");
            return;
        }

        System.out.print("Enter check-in date (yyyy-mm-dd): ");
        LocalDate checkIn = LocalDate.parse(scanner.next());
        System.out.print("Enter check-out date (yyyy-mm-dd): ");
        LocalDate checkOut = LocalDate.parse(scanner.next());
        scanner.nextLine();

        List<Room> availableRooms = hotel.getAvailableRooms(checkIn, checkOut);
        System.out.println("Available Rooms:");
        for (Room room : availableRooms) {
            System.out.printf("%d - %s ($%.2f/day)\n",
                    room.getRoomNumber(), room.getType(), room.getPricePerDay());
        }

        System.out.print("Enter room number to book: ");
        int roomNumber = scanner.nextInt();
        scanner.nextLine();

        if (hotel.bookRoom(customer, roomNumber, checkIn, checkOut)) {
            System.out.println("Booking successful!");
        } else {
            System.out.println("Booking failed! Room might be unavailable.");
        }
    }


    private static void cancelReservation() {
        System.out.print("Enter customer ID: ");
        int customerId = scanner.nextInt();
        Customer customer = hotel.findCustomerById(customerId);
        if (customer == null) {
            System.out.println("Customer not found!");
            return;
        }

        List<Reservation> bookings = customer.getBookingHistory();
        System.out.println("Active Bookings:");
        for (int i = 0; i < bookings.size(); i++) {
            Reservation r = bookings.get(i);
            System.out.printf("%d. Room %d (%s) %s to %s\n",
                    i+1, r.getRoom().getRoomNumber(), r.getRoom().getType(),
                    r.getCheckInDate(), r.getCheckOutDate());
        }

        System.out.print("Enter booking number to cancel: ");
        int bookingNumber = scanner.nextInt();
        scanner.nextLine();

        if (bookingNumber > 0 && bookingNumber <= bookings.size()) {
            Reservation toCancel = bookings.get(bookingNumber-1);
            if (hotel.cancelReservation(toCancel)) {
                System.out.println("Reservation cancelled successfully!");
            } else {
                System.out.println("Cancellation failed!");
            }
        } else {
            System.out.println("Invalid booking number!");
        }
    }

    private static void checkAvailability() {
        System.out.print("Enter check-in date (yyyy-mm-dd): ");
        LocalDate checkIn = LocalDate.parse(scanner.next());
        System.out.print("Enter check-out date (yyyy-mm-dd): ");
        LocalDate checkOut = LocalDate.parse(scanner.next());
        scanner.nextLine();

        List<Room> availableRooms = hotel.getAvailableRooms(checkIn, checkOut);
        System.out.println("Available Rooms:");
        for (Room room : availableRooms) {
            System.out.printf("Room %d - %s ($%.2f/day)\n",
                    room.getRoomNumber(), room.getType(), room.getPricePerDay());
        }
    }

    private static void generateBill() {
        System.out.print("Enter customer ID: ");
        int customerId = scanner.nextInt();
        Customer customer = hotel.findCustomerById(customerId);
        if (customer == null) {
            System.out.println("Customer not found!");
            return;
        }

        List<Reservation> bookings = customer.getBookingHistory();
        if (bookings.isEmpty()) {
            System.out.println("No bookings found for this customer!");
            return;
        }

        System.out.println("Select booking to generate bill:");
        for (int i = 0; i < bookings.size(); i++) {
            Reservation r = bookings.get(i);
            System.out.printf("%d. Room %d (%s) %s to %s\n",
                    i+1, r.getRoom().getRoomNumber(), r.getRoom().getType(),
                    r.getCheckInDate(), r.getCheckOutDate());
        }

        System.out.print("Enter booking number: ");
        int bookingNumber = scanner.nextInt();
        scanner.nextLine();

        if (bookingNumber > 0 && bookingNumber <= bookings.size()) {
            Billing.generateBill(bookings.get(bookingNumber-1));
        } else {
            System.out.println("Invalid booking number!");
        }
        
    }

    private static void viewBookingHistory() {
        System.out.print("Enter customer ID: ");
        int customerId = scanner.nextInt();
        Customer customer = hotel.findCustomerById(customerId);
        if (customer == null) {
            System.out.println("Customer not found!");
            return;
        }

        List<Reservation> bookings = customer.getBookingHistory();
        System.out.println("\nBooking History for " + customer.getName());
        for (Reservation r : bookings) {
            System.out.printf("- Room %d (%s) from %s to %s\n",
                    r.getRoom().getRoomNumber(), r.getRoom().getType(),
                    r.getCheckInDate(), r.getCheckOutDate());
        }
        System.out.println();
    }   
}