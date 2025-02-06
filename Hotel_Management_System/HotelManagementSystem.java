import java.time.LocalDate;
import java.util.*;

public class HotelManagementSystem {
    private static Hotel hotel = new Hotel();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        

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

   