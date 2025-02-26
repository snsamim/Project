import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RestaurantManagementSystem {
    private static List<Customer> customers = new ArrayList<>();
    private static List<FoodItem> menu = new ArrayList<>();
    private static List<Order> orders = new ArrayList<>();
    private static List<Employee> employees = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== RESTAURANT MANAGEMENT SYSTEM ===");
            System.out.println("1. Customer Management");
            System.out.println("2. Menu & Orders");
            System.out.println("3. Employee Management");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int mainChoice = -1;

            try {
                mainChoice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.\n");
                continue;
            }

            switch (mainChoice) {
                case 1:
                    customerManagement(scanner);
                    break;
                case 2:
                    menuAndOrders(scanner);
                    break;
                case 3:
                    employeeManagement(scanner);
                    break;
                case 4:
                    running = false;
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }

    // ------------------------------
    // SECTION 1: CUSTOMER MANAGEMENT
    // ------------------------------
    private static void customerManagement(Scanner scanner) {
        boolean cmRunning = true;
        while (cmRunning) {
            System.out.println("\n--- CUSTOMER MANAGEMENT ---");
            System.out.println("1. Add Customer");
            System.out.println("2. View All Customers");
            System.out.println("3. Back to Main Menu");
            System.out.print("Choose an option: ");

            int choice = -1;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.\n");
                continue;
            }

            switch (choice) {
                case 1:
                    addCustomer(scanner);
                    break;
                case 2:
                    viewAllCustomers();
                    break;
                case 3:
                    cmRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addCustomer(Scanner scanner) {
        try {
            System.out.print("Enter Customer ID: ");
            String customerId = scanner.nextLine();

            System.out.print("Enter Customer Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter Phone Number: ");
            String phone = scanner.nextLine();

            Customer customer = new Customer(customerId, name, phone);
            customers.add(customer);
            System.out.println("Customer added successfully!");
        } catch (InvalidNameException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void viewAllCustomers() {
        if (customers.isEmpty()) {
            System.out.println("No customers found.");
            return;
        }
        System.out.println("\n--- LIST OF CUSTOMERS ---");
        for (Customer c : customers) {
            c.printDetails();
        }
    }

    // ----------------------------
    // SECTION 2: MENU AND ORDERS
    // ----------------------------
    private static void menuAndOrders(Scanner scanner) {
        boolean moRunning = true;
        while (moRunning) {
            System.out.println("\n--- MENU & ORDERS ---");
            System.out.println("1. Add Food Item to Menu");
            System.out.println("2. View Menu");
            System.out.println("3. Place an Order");
            System.out.println("4. View All Orders");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose an option: ");

            int choice = -1;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.\n");
                continue;
            }

            switch (choice) {
                case 1:
                    addFoodItem(scanner);
                    break;
                case 2:
                    viewMenu();
                    break;
                case 3:
                    placeOrder(scanner);
                    break;
                case 4:
                    viewAllOrders();
                    break;
                case 5:
                    moRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addFoodItem(Scanner scanner) {
        try {
            System.out.print("Enter Food ID: ");
            String foodId = scanner.nextLine();

            System.out.print("Enter Food Name: ");
            String foodName = scanner.nextLine();

            System.out.print("Enter Food Price: ");
            double price = Double.parseDouble(scanner.nextLine());

            FoodItem item = new FoodItem(foodId, foodName, price);
            menu.add(item);
            System.out.println("Food item added successfully!");
        } catch (NumberFormatException e) {
            System.out.println("Invalid price. Please enter a valid number.");
        } catch (InvalidPriceException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void viewMenu() {
        if (menu.isEmpty()) {
            System.out.println("No food items in the menu.");
            return;
        }
        System.out.println("\n--- CURRENT MENU ---");
        for (FoodItem item : menu) {
            item.printDetails();
        }
    }

    private static void placeOrder(Scanner scanner) {
        System.out.print("Enter Order ID: ");
        String orderId = scanner.nextLine();

        System.out.print("Enter Customer ID for this order: ");
        String custId = scanner.nextLine();

        Order order = new Order(orderId, custId);

        // Let user pick items from the existing menu
        if (menu.isEmpty()) {
            System.out.println("No items in the menu to order from. Please add menu items first.");
            return;
        }

        System.out.print("How many items do you want to order? ");
        int count;
        try {
            count = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Order canceled.");
            return;
        }

        for (int i = 0; i < count; i++) {
            System.out.println("\n--- SELECT AN ITEM FROM THE MENU ---");
            viewMenu();
            System.out.print("Enter the Food ID you want to add: ");
            String fid = scanner.nextLine();

            // Find the item in the menu
            FoodItem chosen = null;
            for (FoodItem item : menu) {
                if (item.getFoodId().equalsIgnoreCase(fid)) {
                    chosen = item;
                    break;
                }
            }

            if (chosen == null) {
                System.out.println("No menu item found with that ID. Skipping...");
            } else {
                order.addItem(chosen);
                System.out.println("Item added to order!");
            }
        }

        orders.add(order);
        System.out.println("Order placed successfully!");
        System.out.println("Order Summary:");
        order.printDetails();
    }

    private static void viewAllOrders() {
        if (orders.isEmpty()) {
            System.out.println("No orders found.");
            return;
        }
        System.out.println("\n--- ALL ORDERS ---");
        for (Order order : orders) {
            order.printDetails();
        }
    }

    // -----------------------------
    // SECTION 3: EMPLOYEE MANAGEMENT
    // -----------------------------
    private static void employeeManagement(Scanner scanner) {
        boolean emRunning = true;
        while (emRunning) {
            System.out.println("\n--- EMPLOYEE MANAGEMENT ---");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. View Employees' Bonuses");
            System.out.println("4. Back to Main Menu");
            System.out.print("Choose an option: ");

            int choice = -1;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.\n");
                continue;
            }

            switch (choice) {
                case 1:
                    addEmployee(scanner);
                    break;
                case 2:
                    viewEmployees();
                    break;
                case 3:
                    viewEmployeeBonuses();
                    break;
                case 4:
                    emRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addEmployee(Scanner scanner) {
        try {
            System.out.print("Enter Employee ID: ");
            String empId = scanner.nextLine();

            System.out.print("Enter Employee Name: ");
            String empName = scanner.nextLine();

            System.out.print("Enter Employee Salary: ");
            double salary = Double.parseDouble(scanner.nextLine());

            System.out.print("Enter Role (Manager/Waiter): ");
            String role = scanner.nextLine().trim().toLowerCase();

            Employee emp;
            if (role.equals("manager")) {
                emp = new Manager(empId, empName, salary);
            } else if (role.equals("waiter")) {
                emp = new Waiter(empId, empName, salary);
            } else {
                System.out.println("Invalid role. Employee not added.");
                return;
            }

            employees.add(emp);
            System.out.println("Employee added successfully!");
        } catch (NumberFormatException e) {
            System.out.println("Invalid salary input. Please enter a valid number.");
        } catch (InvalidSalaryException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void viewEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }
        System.out.println("\n--- LIST OF EMPLOYEES ---");
        for (Employee e : employees) {
            e.printDetails();
        }
    }

    private static void viewEmployeeBonuses() {
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }
        System.out.println("\n--- EMPLOYEE BONUSES ---");
        for (Employee e : employees) {
            // Print employee basic info
            e.printDetails();
            // Then print the bonus
            System.out.printf("Bonus: $%.2f%n", e.calculateBonus());
            System.out.println("----------------------------------");
        }
    }
}
