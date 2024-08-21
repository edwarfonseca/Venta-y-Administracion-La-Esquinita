package uptc.proyectofx.Employed;

public class Product {
    private final String name;
    private final double price;
    private final int quantity;
    private final String imagePath;

    public Product(String name, double price, int quantity, String imagePath) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getImagePath() {
        return imagePath;
    }
}
