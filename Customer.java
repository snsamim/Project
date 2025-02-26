public class Customer implements Printable {
    private String customerId;
    private String name;
    private String phoneNumber;

    public Customer(String customerId, String name, String phoneNumber) throws InvalidNameException {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidNameException("Customer name cannot be empty.");
        }
        this.customerId = customerId;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    // Optional getters if needed
    public String getCustomerId() { return customerId; }
    public String getName()        { return name;        }
    public String getPhoneNumber() { return phoneNumber; }

    @Override
    public void printDetails() {
        System.out.printf("Customer ID: %s | Name: %s | Phone: %s%n",
                customerId, name, phoneNumber);
    }
}
