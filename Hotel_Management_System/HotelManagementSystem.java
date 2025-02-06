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
}