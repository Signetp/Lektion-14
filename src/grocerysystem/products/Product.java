package grocerysystem.products;

import java.util.UUID;

public abstract class Product implements Expirable, Comparable<Product> {

    private UUID serial;
    private String name;
    private double price;

    public Product(String name, double price) {
        this.serial = UUID.randomUUID();
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public UUID getSerial() {
        return serial;
    }

    @Override
    public boolean isExpired() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int compareTo(Product p) {
        return (int) (this.price - p.price);
        
    }
}
