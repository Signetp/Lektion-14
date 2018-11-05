package grocerysystem;

import grocerysystem.products.ExpiredProductAddedException;
import grocerysystem.products.FoodProduct;
import grocerysystem.products.NonFoodProduct;
import grocerysystem.products.Product;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GroceryInventory {

    public static void main(String[] args) {
        ProductDatabase inventory = new ProductDatabase();

        FoodProduct p1;
        try {
            p1 = foodProductCreator("Milk", 6.95, 1000000000, 5);
            inventory.addProduct(p1);
        } catch (ExpiredProductAddedException ex) {
        }

        FoodProduct p2;
        try {
            p2 = foodProductCreator("Cream", 12.95, 800000000, 3);
            inventory.addProduct(p2);
        } catch (ExpiredProductAddedException ex) {
        }

        FoodProduct p3;
        try {
            p3 = foodProductCreator("Cake", 18.00, -8000000000L, 20);
            inventory.addProduct(p3);
        } catch (ExpiredProductAddedException ex) {
        }

        NonFoodProduct p4 = new NonFoodProduct("Reol", 295.5, new String[]{"Bøg", "Maling"});
        inventory.addProduct(p4);

        NonFoodProduct p5 = new NonFoodProduct("Stol", 29.5, new String[]{"Plast", "Mere plast"});
        inventory.addProduct(p5);

        NonFoodProduct p6 = new NonFoodProduct("Samsung Galaxy S9", 7899.95, new String[]{"Snapdragon something something", "Ram", "Screen"});
        inventory.addProduct(p6);

        System.out.println("Total price: " + inventory.getTotalPrice() + "\n");
        System.out.println(inventory.printInventory());

        System.out.println(" the list is sorted by the names length ");
        for (Product p : inventory.sortedByNameLength()) {
            System.out.println(p);
        }

        System.out.println(" ");
        System.out.println(" The list is sorted by price ");
        for (Product p : inventory.sortedByPrice()) {
            System.out.println(p);
        }
    }

    private static FoodProduct foodProductCreator(String name, double price, long expOffsetMs, int temp) throws ExpiredProductAddedException {
        Date d = new Date();
        d.setTime(d.getTime() + expOffsetMs);
        return new FoodProduct(name, price, d, temp);
    }

}
