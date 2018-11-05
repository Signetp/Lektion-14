/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grocerysystem.products;

import java.util.Comparator;

/**
 *
 * @author signethuesenpedersen
 */
public class ProductNameLengthComparator implements Comparator<Product>{

    @Override
    public int compare(Product o1, Product o2) {
        return o1.getName().length() - o2.getName().length();
    }
    
}
