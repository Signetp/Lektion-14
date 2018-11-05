package grocerysystem;

import java.text.DecimalFormat;
import java.util.UUID;
import grocerysystem.products.Product;
import grocerysystem.products.ProductNameLengthComparator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class ProductDatabase {

    private HashMap<UUID, Product> products;
    
    public ProductDatabase(){
        this.products = new HashMap<>();          
    }

    public void addProduct(Product p) {

        products.put(p.getSerial(), p);
    }

    public void removeProduct(UUID serial) {
        products.remove(serial);
    }

    public String printInventory() {
        //Use StringBuild to create a combined string from multiple strings.
        StringBuilder sb = new StringBuilder();
        //Append adds String to the end of the combined string.
        sb.append("Lagerbeholdning:\n");

        for (Product p : products.values()) {
            sb.append("Name: ").append(p.getName()).append("\t")
                    .append("Price: ").append(p.getPrice()).append("\t")
                    .append("Serial: ").append(p.getSerial()).append("\n");
        }
        //Call toString on the StringBuilder object, to get the String representation.
        return sb.toString();
    }

    public String getTotalPrice() {
        double result = 0;
        //Loop through all products. Accumulate prices.
        for (Product p : products.values()) {
            result += p.getPrice();
        }
        //Use DecimalFormat class to get a currency representation of the double in local currency.
        return DecimalFormat.getCurrencyInstance().format(result);
    }

    public HashMap<UUID, Product> getProducts() {
        //Return a copy of the array, so that the database is not exposed to external actors.
        return new HashMap<>(products);
    }

    public void removeExpiredFoods() {
        ArrayList<Product> expiredProducts = new ArrayList<>();
        for (Product p : products.values()) {
            try {
                if (p.isExpired()) {
                    // can do products.remove(p) as we are iterating the list. Save for later.
                    expiredProducts.add(p);
                }
            } catch (UnsupportedOperationException ex) {
                //Encountered a NonFoodProduct-object. Nothing to worry about.
            }
        }
        System.out.println("Removing a total of " + expiredProducts.size() + " expired product(s)");
        for(Product expiredProduct : expiredProducts)
        {
            products.remove(expiredProduct.getSerial());
        }
    }
    
    public List<Product> sortedByNameLength(){
        List<Product> sortedList = new ArrayList(); 
        
        for (Product p : products.values()){
            sortedList.add(p);
        }
        Collections.sort(sortedList, new ProductNameLengthComparator());
        return sortedList;
    }
    public List<Product> sortedByPrice(){
        List<Product> sortedPriceList = new ArrayList(); 
        
        for (Product p : products.values()){
            sortedPriceList.add(p);
        }
        Collections.sort(sortedPriceList);
        return sortedPriceList;
    }
    
}
