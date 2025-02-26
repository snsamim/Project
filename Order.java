import java.util.ArrayList;
import java.util.List;

public class Order implements Printable {
    private String orderId;
    private String customerId;
    private List<FoodItem> orderedItems;
    private double totalPrice;

    public Order(String orderId, String customerId) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderedItems = new ArrayList<>();
        this.totalPrice = 0.0;
    }

    public void addItem(FoodItem item) {
        orderedItems.add(item);
        totalPrice += item.getPrice();
    }

    // Optional getters if needed
    public String getOrderId()         { return orderId;      }
    public String getCustomerId()      { return customerId;   }
    public List<FoodItem> getItems()   { return orderedItems; }
    public double getTotalPrice()      { return totalPrice;   }

    @Override
    public void printDetails() {
        System.out.printf("Order ID: %s | Customer ID: %s | Total: $%.2f%n",
                orderId, customerId, totalPrice);
    }
}
