public class FoodItem implements Printable {
    private String foodId;
    private String name;
    private double price;

    public FoodItem(String foodId, String name, double price) throws InvalidPriceException {
        if (price < 0) {
            throw new InvalidPriceException("Food price cannot be negative.");
        }
        this.foodId = foodId;
        this.name = name;
        this.price = price;
    }

    // Optional getters if needed
    public String getFoodId() { return foodId; }
    public String getName()   { return name;   }
    public double getPrice()  { return price;  }

    @Override
    public void printDetails() {
        System.out.printf("Food ID: %s | Name: %s | Price: $%.2f%n",
                foodId, name, price);
    }
}
